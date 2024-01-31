"""
Copyright (c) MinWu - All Rights Reserved
Unauthorized copying of this file, via any medium is strictly prohibited
Proprietary and confidential
Author: MinWu <wumin199@126.com>, 2024/01/30
"""

import re
import glob
import os

script_dir = os.path.dirname(os.path.realpath(__file__))

# https://blog.51cto.com/u_16131663/6416229


def read_md_files():
    """
    Read all .md files in this repository and its subdirectories.

    Returns:
        List[str]: A list of file paths to the .md files.
    """
    md_files = glob.glob(os.path.join(script_dir, "../source/**/*.md"), recursive=True)
    return md_files


def replace_url_to_raw(md_file_path: str):
    """
    Replaces the URL in the given Markdown file with the raw URL.

    Args:
        md_file_path (str): The path to the Markdown file.

    Returns:
        None
    """
    with open(md_file_path, "r", encoding='utf-8') as f:
        content = f.read()
    if re.search(r"https://github.com/wumin199/(.*)/blob/main/(.*\.(jpg|png|gif|bmp))",
                 content) is None:
        return
    new_content = re.sub(r"https://github.com/wumin199/(.*)/blob/main/(.*\.(jpg|png|gif|bmp))",
                         r"https://github.com/wumin199/\1/raw/main/\2", content)
    with open(md_file_path, "w", encoding='utf-8') as f:
        f.write(new_content)
    print("update: {}".format(md_file_path))


def replace_url_to_main(md_file_path: str):
    """
    Replaces the URL in the given Markdown file with the main URL.

    Args:
        md_file_path (str): The path to the Markdown file.

    Returns:
        None
    """
    with open(md_file_path, "r", encoding='utf-8') as f:
        content = f.read()
    if re.search(r"https://github.com/wumin199/(.*)/raw/main/(.*\.(jpg|png|gif|bmp))",
                 content) is None:
        return
    new_content = re.sub(r"https://github.com/wumin199/(.*)/raw/main/(.*\.(jpg|png|gif|bmp))",
                         r"https://github.com/wumin199/\1/blob/main/\2", content)
    with open(md_file_path, "w", encoding='utf-8') as f:
        f.write(new_content)
    print("update: {}".format(md_file_path))


if __name__ == "__main__":
    md_files = read_md_files()
    for md_file in md_files:
        replace_url_to_raw(md_file)
        # replace_url_to_main(md_file)
