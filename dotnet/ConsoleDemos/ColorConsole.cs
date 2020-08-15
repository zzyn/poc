using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace ConsoleReadRate
{
    class Program
    {

        public static void ShowProcess(
            string headInfo
           , string tailInfo
            , int postion
           ) 

        {
            string OneSpace = " ";
            ConsoleColor colorBack = Console.BackgroundColor;
            ConsoleColor colorFore = Console.ForegroundColor;
           
            for (int i = 0; i <= 100; i++)
            {
                int nHead = postion;
                int nProcess = postion + 1;
                int nTail = postion + 2;
                Console.SetCursorPosition(0, nHead);
                Console.Write(headInfo);
                
                Console.BackgroundColor = ConsoleColor.Yellow;
                Console.SetCursorPosition((i * 3) / 4, nProcess);
                Console.Write(OneSpace);
                Console.BackgroundColor = colorBack;

                Console.ForegroundColor = ConsoleColor.Green;
                Console.SetCursorPosition(75, nProcess);
                Console.Write("{0}%", i);
                Console.ForegroundColor = colorFore;


                Console.SetCursorPosition(0, nTail);
                Console.Write(tailInfo);
               
                System.Threading.Thread.Sleep(100);
            }

            Console.BackgroundColor = colorBack;
        }

        public static void ShowProcess(
            string headInfo
           , string tailInfo
            , int top
            , int left
           )
        {
            string OneSpace = " ";
            ConsoleColor colorBack = Console.BackgroundColor;
            ConsoleColor colorFore = Console.ForegroundColor;

           
            int nHead = top;
            int nProcess = top + 1;
            int nTail = top + 2;
            Console.SetCursorPosition(0, nHead);
            Console.Write(headInfo);

            Console.BackgroundColor = ConsoleColor.Yellow;
            Console.SetCursorPosition((left * 3) / 4, nProcess);
            Console.Write(OneSpace);
            Console.BackgroundColor = colorBack;

            Console.ForegroundColor = ConsoleColor.Green;
            Console.SetCursorPosition(75, nProcess);
            Console.Write("{0}%", left);
            Console.ForegroundColor = colorFore;


            Console.SetCursorPosition(0, nTail);
            Console.Write(tailInfo);

            System.Threading.Thread.Sleep(100);
            

            Console.BackgroundColor = colorBack;
        }

       
        static void Main(string[] args)
        {

            //ShowProcess("test", "123",1);

            //ShowProcess("test", "123", 10);
            int total = 6000;
            int step = total / 100;
            int nProcess = 0;

            while (nProcess <= total) 
            {
                string aa = string.Format("{0}/{1}",nProcess,total);
                int left = (nProcess / step);
                ShowProcess("Hello World!", aa, 1, left);
                nProcess++;
            }


            //ShowProcess("test", "123",10);
            //bool isBreak = false;
            //ConsoleColor colorBack = Console.BackgroundColor;
            //ConsoleColor colorFore = Console.ForegroundColor;

            

            //// 第一行信息 
            //Console.WriteLine(" *********** abc now working...****** ");

            //// 第二行绘制进度条背景 
            //Console.BackgroundColor = ConsoleColor.DarkCyan;
            //for (int i = 0; i < 75; i++)
            //{
            //    Console.Write(OneSpace);
            //}
            //Console.Write(OneSpace);
            //Console.BackgroundColor = colorBack;
            //// 第三行输出进度 
            //Console.Write("0%");
            ////// 第四行输出提示,按下回车可以取消当前进度 
            ////Console.WriteLine(" <Press Enter To Break.> ");

            //// -----------------------上面绘制了一个完整的工作区域,下面开始工作

            //// 开始控制进度条和进度变化 
            //for (int i = 0; ++i <= 100; )
            //{
            //    //// 先检查是否有按键请求,如果有,判断是否为回车键,如果是则退出循环 
            //    //if (Console.KeyAvailable 
            //    //    && System.Console.ReadKey(true).Key == ConsoleKey.Enter)
            //    //{
            //    //    isBreak = true;
            //    //    break;
            //    //}
            //    // 绘制进度条进度 
            //    // 设置进度条颜色 
            //    Console.BackgroundColor = ConsoleColor.Yellow;
            //    // 设置光标位置,参数为第几列和第几行 
            //    Console.SetCursorPosition((i*3)/4 , 1); 
            //    Console.Write(OneSpace); // 移动进度条 
            //    Console.BackgroundColor = colorBack; // 恢复输出颜色
            //    // 更新进度百分比,原理同上. 
            //    Console.ForegroundColor = ConsoleColor.Green;
            //    Console.SetCursorPosition(75, 1);
            //    Console.Write("{0}% ", i);
            //    Console.ForegroundColor = colorFore;
            //    // 模拟实际工作中的延迟,否则进度太快 
            //    System.Threading.Thread.Sleep(100);
            //}
            //// 工作完成,根据实际情况输出信息,而且清楚提示退出的信息 
            //Console.SetCursorPosition(0, 3);
            //Console.Write(isBreak ? " break!!! " : " finished. ");
            //Console.WriteLine("                        ");
            //// 等待退出 
            //Console.ReadKey(true);
        }

      
    }
}
