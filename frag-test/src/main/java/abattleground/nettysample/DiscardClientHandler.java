package abattleground.nettysample;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DiscardClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.print("Server Said:");
            while (in.isReadable()) { // (1)
                System.out.print((char) in.readByte());
                System.out.flush();
            }
            ctx.close();
        } finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        //String msg = "{\"deviceId\":\"000000000001\",\"deviceType\":\"12\",\"frameTime\":1536201680689,\"x\":116.463,\"y\":39.9212}";
//        String msg = "{\"deviceId\":\"000000000001\",\"deviceType\":\"12\",\"frameTime\":1536201680689,\"loc\":{\"coordinates\":[116.463,39.9212],\"type\":\"Point\",\"x\":116.463,\"y\":39.9212}}";
//        ByteBuf encoded = ctx.alloc().buffer(msg.length());
//        encoded.writeBytes(msg.getBytes());
//        ctx.write(encoded);
//        ctx.flush();

//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("11111111");
//
//                String x = String .format("%.4f",116+Math.random());
//                String y = String .format("%.4f",39+Math.random());
//
//                String msg = "{\"deviceId\":\"000000000001\",\"deviceType\":\"12\",\"frameTime\":1536201680689," +
//                        "\"loc\":{\"coordinates\":["+x+","+y+"],\"type\":\"Point\",\"x\":"+x+",\"y\":"+y+"}}";
//                ByteBuf encoded = ctx.alloc().buffer(msg.length());
//                encoded.writeBytes(msg.getBytes());
//                ctx.write(encoded);
//                ctx.flush();
//            }
//        },2000);

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("11111111");
                String x = String.format("%.4f", 116 + (Math.random()/1000));
                String y = String.format("%.4f", 39 + (Math.random()/1000));
                String time = String.valueOf(System.currentTimeMillis());
                String msg = "{\"deviceId\":\"000000000001\",\"deviceType\":\"12\",\"frameTime\":" + time + "," +
                        "\"loc\":{\"coordinates\":[" + x + "," + y + "],\"type\":\"Point\",\"x\":" + x + ",\"y\":" + y + "}}";
                ByteBuf encoded = ctx.alloc().buffer(msg.length());
                encoded.writeBytes(msg.getBytes());
                ctx.write(encoded);
                ctx.flush();
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}

