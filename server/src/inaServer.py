import sys
import socket

class IPC(object):
    def __init__(self):
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.s.connect(("localhost", 32000))
        self.fid = self.s.makefile(mode='rw') # file wrapper to read lines
        self.listenLoop() # wait listening for updates from server

    def listenLoop(self):
        fid = self.fid
        print ("connected")
        while True:
            while True:
                line = fid.readline();
                if line and line[0]=='.':
                    break

            print ("request received, sending: U=6.2")
            fid.write("6.2")
            fid.flush()

if __name__ == '__main__':
    st = IPC()
