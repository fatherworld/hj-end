package hty.example.updownfiles2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hty.example.updownfiles2.entity.person.user;
import io.swagger.models.auth.In;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import hty.example.updownfiles2.controller.updownfile;
import springfox.documentation.oas.annotations.EnableOpenApi;
import hty.example.updownfiles2.algorithms.algorithmclassify;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;

@SpringBootApplication
@EnableOpenApi
@MapperScan("hty.example.updownfiles2.mapper") //这里使得mapper里面的东西生效
public class Updownfiles2Application {
	static void testwithString(String json) throws JsonProcessingException {
		json = "{ \"uername\":\"John\", \"id\":30, \"passwd\":\"New York\" }";
		ObjectMapper objectMapper = new ObjectMapper();
		user usr = objectMapper.readValue(json, user.class);
		System.out.println(usr.toString());
	}
	//使用json文件进行测试
	void testwithFile(String filepath) throws IOException {
		//先将读取的json文件保存成String字符串暂存
		File file = new File(filepath);
		FileReader fileReader = new FileReader(file);
		Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
		int ch = 0;
		StringBuffer sb = new StringBuffer();
		while ((ch = reader.read()) != -1) {
			sb.append((char) ch);
		}
		fileReader.close();
		reader.close();
		String jsonStr = sb.toString();
		//然后使用testwithString进行解析、
		testwithString(jsonStr);
	}
	public static void main(String[] args) {
		SpringApplication.run(Updownfiles2Application.class, args);
	}
}
