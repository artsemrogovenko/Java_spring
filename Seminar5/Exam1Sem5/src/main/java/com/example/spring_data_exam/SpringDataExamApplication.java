package com.example.spring_data_exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/** обмен значениями
 * 	  {
 * 		"senderAccountId": 2,
 * 		"receiverAccountId": 1,
 * 		"amount": 500
 *     }
 * */
@SpringBootApplication
public class SpringDataExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataExamApplication.class, args);
	}

}
