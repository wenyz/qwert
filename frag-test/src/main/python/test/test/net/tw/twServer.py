from twisted.internet import protocol,reactor
from time import ctime

PORT = 21567

class TSServProtocol(protocol.Protocol):
    def connectionMade(self):
        clnt = self.clnt = self.transport.getPeer().host
        print('...connected from :',clnt)

    def dataReceived(self, data):
        self.transport.write(bytes('[%s] %s' %(bytes(ctime(),'utf-8'),data),'utf-8'))

factory = protocol.Factory()
factory.protocol = TSServProtocol
print('waiting for connection...')
reactor.listenTCP(PORT,factory)
reactor.run()