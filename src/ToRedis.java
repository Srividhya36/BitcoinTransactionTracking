import redis.clients.jedis.Jedis;

public class ToRedis {
	public void abc()
	{
		System.out.println("Hi");
		Jedis jedis = new Jedis("localhost"); 
		jedis.set("tutorial-name", "Redis tutorial"); 
		jedis.expire("tutorial-name", 150000000);
		System.out.println("TTL:" + jedis.ttl("tutorial-name"));
		jedis.close();
	}
}
