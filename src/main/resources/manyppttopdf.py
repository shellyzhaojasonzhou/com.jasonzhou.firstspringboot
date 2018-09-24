from win32com import client
from sys import argv
p = client.Dispatch("PowerPoint.Application")
ppt = p.Presentations.Open(argv[1], False, False, False)
ppt.ExportAsFixedFormat(argv[2], 2, PrintRange=None)
p.Quit()
# 
# 作者：sky2016
# 链接：https://www.jianshu.com/p/020f4f62beb9
# 來源：简书
# 简书著作权归作者所有，任何形式的转载都请联系作者获得授权并注明出处。