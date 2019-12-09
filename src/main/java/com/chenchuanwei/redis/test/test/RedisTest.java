/**
 * 
 */
package com.chenchuanwei.redis.test.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bawei.util.RandomUtil;
import com.chenchuanwei.redis.test.bean.User;

/**
 * @author ccw
 *
 * 2019年12月9日
 */
@SuppressWarnings("all")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class RedisTest {

	@Resource
	private RedisTemplate redisTemplate;
	
	//使用JDK系列化方式保存5万个user随机对象到Redis
	@Test
	public void redisJdkSave(){
		
		ValueOperations ops = redisTemplate.opsForValue();
		
		long time1 = System.currentTimeMillis();
		
		for (int i = 1; i <= 50000; i++) {
			
			User u=new User();
			
			//id赋值
			u.setId(i);
			
			//姓名赋值
			u.setName(RandomUtil.getRandomChar(3));
			
			//性别赋值
			u.setSex(RandomUtil.getRandomSex());
			
			//手机号赋值
			u.setPhone(RandomUtil.getRandomPhone());
			
			//邮箱赋值
			u.setEmail(RandomUtil.getRandomEmail());
			
			//生日赋值
			u.setBirthday(RandomUtil.getRandomDate(1949, 2001));
			
			//将数据保存到redis中
			ops.set(String.valueOf(i), u);
			
		}
		
		long time2 = System.currentTimeMillis();
		
		long time =time2-time1;
		
		System.out.println("序列化方式:jdk	所消耗时间:"+time+"	保存数量:50000");
		
	}
	
	//使用JSON系列化方式保存5万个user随机对象到Redis
	@Test
	public void redisJsonSave(){
		
		ValueOperations ops = redisTemplate.opsForValue();
		
		long time1 = System.currentTimeMillis();
		
		for (int i = 1; i <= 50000; i++) {
			
			User u=new User();
			
			//id赋值
			u.setId(i);
			
			//姓名赋值
			u.setName(RandomUtil.getRandomChar(3));
			
			//性别赋值
			u.setSex(RandomUtil.getRandomSex());
			
			//手机号赋值
			u.setPhone(RandomUtil.getRandomPhone());
			
			//邮箱赋值
			u.setEmail(RandomUtil.getRandomEmail());
			
			//生日赋值
			u.setBirthday(RandomUtil.getRandomDate(1949, 2001));
			
			//将数据保存到redis中
			ops.set(String.valueOf(i), u);
			
		}
		
		long time2 = System.currentTimeMillis();
		
		long time =time2-time1;
		
		System.out.println("序列化方式:json	所消耗时间:"+time+"	保存数量:50000");
		
	}
	
	//使用Redis的Hash类型保存5万个user随机对象到Redis
	@Test
	public void redisHashSave(){
		
		long time1 = System.currentTimeMillis();
		
		for (int i = 1; i <= 50000; i++) {
			
			BoundHashOperations ops = redisTemplate.boundHashOps(String.valueOf(i));
			
			User u=new User();
			
			//id赋值
			u.setId(i);
			
			//姓名赋值
			u.setName(RandomUtil.getRandomChar(3));
			
			//性别赋值
			u.setSex(RandomUtil.getRandomSex());
			
			//手机号赋值
			u.setPhone(RandomUtil.getRandomPhone());
			
			//邮箱赋值
			u.setEmail(RandomUtil.getRandomEmail());
			
			//生日赋值
			u.setBirthday(RandomUtil.getRandomDate(1949, 2001));
			
			//将数据保存到redis中
			ops.put(String.valueOf(i), u.toString());
			
		}
		
		long time2 = System.currentTimeMillis();
		
		long time =time2-time1;
		
		System.out.println("序列化方式:hash	所消耗时间:"+time+"	保存数量:50000");
		
	}
} 
