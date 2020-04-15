package com.summer.demos.ratelimiter;//package com.test.ratelimiter;
//
//import lombok.NonNull;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//
//
//public class StrictGlobalRateLimiter extends RateLimiter {
//
//    private static final String lua = "local ret = \"true\" \n local current = redis.call(\"incrby\",KEYS[1], ARGV[1]) \n "
//            + "if current > tonumber(ARGV[2]) then \n redis.call(\"decrby\",KEYS[1], ARGV[1]) \n ret = \"false\" \n end \n"
//            + "if redis.call(\"pttl\",KEYS[1]) < 0 then \n redis.call(\"pexpire\", KEYS[1], ARGV[3]) end \n return ret";
//
//    private final byte[] key;
//    private final JedisPool pool;
//    private volatile byte[] qps;
//
//    private byte[] timeoutInMills = "1000".getBytes();
//
//    public StrictGlobalRateLimiter(@NonNull JedisPool pool, @NonNull String key, int threshold) {
//        super(threshold);
//        this.pool = pool;
//        this.key = key.getBytes();
//    }
//
//    public void setLimitThreshold(int threshold) {
//        super.setLimitThreshold(threshold);
//        qps = String.valueOf(threshold).getBytes();
//    }
//
//    @Override
//    protected boolean fastPermit(int permits) {
//        if (pool == null)
//            return true;
//
//        try (Jedis jedis = pool.getResource()) {
//            if (qps == null)
//                return true;
//            byte[] result = (byte[]) jedis.eval(lua.getBytes(), 1, key, String.valueOf(permits).getBytes(), qps, timeoutInMills);
//            return Boolean.valueOf(new String(result));
//        } catch (Throwable ex) {
//            elog.warn(ex.getMessage());
//        }
//        return true;
//    }
//
//    @Override
//    protected long predict(int permits) {
//        return -1;
//    }
//}
