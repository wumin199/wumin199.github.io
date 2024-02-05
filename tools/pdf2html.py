"""
Copyright (c) MinWu - All Rights Reserved
Unauthorized copying of this file, via any medium is strictly prohibited
Proprietary and confidential
Author: MinWu <wumin199@126.com>, 2024/02/05
"""

import glob
import subprocess


def read_pdf_files(pdf_dir):
    pdf_files = glob.glob(pdf_dir + "/*.pdf")
    return pdf_files


def can_run_pdf2htmlEx():
    try:
        # 尝试运行命令
        return_code = subprocess.call("pdf2htmlEX -v")
        # 如果命令成功运行，返回True
        if return_code != 0:
            raise Exception("pdf2htmlEX could not be found, install: https://github.com/pdf2htmlEX/pdf2htmlEX")
    except Exception:
        # 如果命令不存在，返回False
        raise Exception("pdf2htmlEX could not be found, install: https://github.com/pdf2htmlEX/pdf2htmlEX")


def pdf2htmlEX(file_dir: str, dest_dir: str):
    """
    Convert a PDF file to HTML using pdf2htmlEX.
    Args:
        file_dir (str): The directory of the PDF file.
        dest_dir (str): The destination directory for the generated HTML file.
    Returns:
        None
    """
    # 生成html文件
    subprocess.call("pdf2htmlEX " + file_dir + " --dest-dir " + dest_dir, shell=True)


if __name__ == "__main__":
    can_run_pdf2htmlEx()
    pass
