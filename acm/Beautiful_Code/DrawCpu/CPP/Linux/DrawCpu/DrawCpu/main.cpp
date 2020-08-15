//
//  main.cpp
//  DrawCpu 编程之美
//
//  Created by Zack Zou on 16/11/21.
//  Copyright © 2016 Zack Zou. All rights reserved.
//

#include <iostream>

#include <time.h>
#include <sys/time.h>
#include <unistd.h>
#include <stdlib.h>
#include <math.h>

#define DWORD unsigned long
#define UINT64 unsigned long long
const double SPLIT = 0.01;
const int COUNT = 200;
const double PI = 3.14159265;
const int INTERVAL = 300;

int main(int argc, const char * argv[]) {

    struct timeval tms;
    DWORD busySpan[COUNT];
    DWORD idleSpan[COUNT];
    int half = INTERVAL/2, i;
    double radian = 0.0;
    for(i = 0; i < COUNT; ++i)
    {
        busySpan[i] = (DWORD)(half + (sin(PI * radian) * half));
        idleSpan[i] = INTERVAL - busySpan[i];
        radian += SPLIT;
    }

    int j = 0;
    while(1)
    {
        j = j % COUNT;
        timerclear(&tms);
        gettimeofday(&tms,NULL);
        clock_t startTime = clock();
        while((clock()-startTime) <= busySpan[j]);

        if(usleep(idleSpan[j]*1000))  //精确到微秒（百万分之一秒）的函数
            exit(-1);
        j++;
    }
    return 0;
}
