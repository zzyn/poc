using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;
using System.Threading;

namespace DrawCpu
{
    class Program
    {
        /// <summary>
        /// 获取时钟频率
        /// </summary>
        /// <returns></returns>
        [DllImport("kernel32.dll")]
        static extern uint GetTickCount();

        //SetThreadAffinityMask 指定hThread 运行在 核心 dwThreadAffinityMask  
        [DllImport("kernel32.dll")]
        static extern UIntPtr SetThreadAffinityMask(IntPtr hThread,
                            UIntPtr dwThreadAffinityMask);
        //得到当前线程的handler  
        [DllImport("kernel32.dll")]
        static extern IntPtr GetCurrentThread();

        static void Main(string[] args)
        {
            Thread t1 = new Thread(new ParameterizedThreadStart(sinaG));
            Console.Write("Which core you will to use (Start from 0):");
            string core = Console.ReadLine();
            int coreNumber = 0;
            try
            {
                coreNumber = Int32.Parse(core);
            }
            catch
            {
                coreNumber = 0;
            }
            t1.Start(coreNumber);
        }
        static void sinaG(object coreNumber)
        {
            int core = 0;
            try
            {
                core = (int)coreNumber;
            }
            catch
            {
                core = 0;
            }
            SetThreadAffinityMask(GetCurrentThread(), new UIntPtr(SetCpuID(core)));
            //指定在核心1上运行  
            //SetThreadAffinityMask(GetCurrentThread(), new UIntPtr(SetCpuID(0)));  
            //指定在核心2上运行  
            //SetThreadAffinityMask(GetCurrentThread(), new UIntPtr(SetCpuID(1)));  
            //指定在核心3上运行  
            //SetThreadAffinityMask(GetCurrentThread(), new UIntPtr(SetCpuID(2)));  
            //指定在核心4上运行  
            //SetThreadAffinityMask(GetCurrentThread(), new UIntPtr(SetCpuID(3)))  
            //split*count=2;也就是正弦函数的周期2 Pi，也就是把一个周期的细分为200份  
            double split = 0.01;
            int count = 200;
            double pi = 3.1415962525;
            //工作周期 300 ms  
            int interval = 300;
            //每个工作周期里工作和空闲的毫秒数  
            int[] busySpan = new int[count];
            int[] idealSpan = new int[count];
            //根据正弦函数计算并填入每个工作周期的工作和空闲毫秒数  
            int half = interval / 2;
            double radian = 0.0;
            for (int i = 0; i < count; i++)
            {
                busySpan[i] = (int)(half + Math.Sin(pi * radian) * half);
                idealSpan[i] = interval - busySpan[i];
                radian += split;
            }
            uint startTime = 0;
            int j = 0;
            while (true)
            {
                j = j % count;
                startTime = GetTickCount();
                while ((GetTickCount() - startTime) <= busySpan[j])
                {
                    ;
                }
                Thread.Sleep(idealSpan[j]);
                j++;
            }
        }
        //函数中的参数 dwThreadAffinityMask 为无符号长整型，用位标识那个核心  
        //比如：为简洁使用四位表示  
        //0x0001表示核心1，  
        //0x0010表示核心2，  
        //0x0100表示核心3，  
        //0x1000表示核心4  
        static ulong SetCpuID(int id)
        {
            ulong cpuid = 0;
            if (id < 0 || id >= System.Environment.ProcessorCount)
            {
                id = 0;
            }
            cpuid |= 1UL << id;
            return cpuid;
        }
    }
}