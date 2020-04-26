import os

PATH = os.path.abspath('.')


def getPath(base: str, file: str) -> str:
    segments = base.split('/')
    index = segments.index('java')

    return '.'.join(segments[index + 1:]) + '.' + file.rsplit('.', maxsplit=1)[0]


java = {getPath(base, file): os.path.join(base, file) for base, _, files in os.walk(PATH) if
        'build' not in base and 'src' in base and not 'test' in base and not 'codegen' in base and not 'androidTest' in base for
        file in files if file.endswith('.java') or file.endswith('.kt')}

imports = {line.split()[-1].rstrip(';') for file in java.values() for line in open(file, encoding='utf-8') if
           line.startswith('import ')}

layouts = {}

unused_classes = set(java.keys()) - imports


def find_usage(file: str, clazz: str) -> bool:
    clazz = clazz.split('.')[-1]

    location = os.path.split(file)[0]

    class_in_same_package = [os.path.join(location, file) for file in os.listdir(location) if os.path.isfile(os.path.join(location, file))]

    if len(class_in_same_package) <= 0:
        return False

    class_in_same_package.remove(file)
    # print(class_in_same_package)

    def lines():
        for j_clazz in class_in_same_package:
            with open(j_clazz, encoding='utf-8') as f:
                yield from f

    for line in lines():
        if clazz in line:
            return True

    return False


index = 1
for clazz in unused_classes:
    file = java[clazz]

    if not find_usage(file, clazz):
        os.remove(file)
        print(f'unused class: {index} {clazz}')
        index += 1
