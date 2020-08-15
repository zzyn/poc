package com.mq.kafka.demo.service.impl;

import com.mq.kafka.demo.entity.Student;
import com.mq.kafka.demo.mapper.StudentMapper;
import com.mq.kafka.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
