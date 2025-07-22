import os
import sys

def png_to_java_byte_array(file_path, var_name):
    with open(file_path, "rb") as f:
        data = f.read()

    java_array_lines = []
    java_array_lines.append(f"byte[] {var_name} = new byte[] {{")
    line = "    "
    for i, b in enumerate(data):
        line += f"(byte)0x{b:02X}, "
        if (i + 1) % 16 == 0:
            java_array_lines.append(line)
            line = "    "
    if line.strip():
        java_array_lines.append(line)
    java_array_lines.append("};\n")
    return "\n".join(java_array_lines)


if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python png_to_java_byte_array.py path/to/directory")
        sys.exit(1)

    dir_path = sys.argv[1]
    if not os.path.isdir(dir_path):
        print("Provided path is not a directory.")
        sys.exit(1)

    for file_name in os.listdir(dir_path):
        if file_name.lower().endswith(".png"):
            full_path = os.path.join(dir_path, file_name)
            var_name = os.path.splitext(file_name)[0].replace("-", "_").replace(" ", "_")
            print(png_to_java_byte_array(full_path, var_name))
