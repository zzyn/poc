using System;
using System.Threading;

namespace AsynchThread
{
    /// <summary>
    /// DemoAsynchTask ��ժҪ˵����
    /// </summary>
    public class DemoAsynchTask : Task
    {
        public DemoAsynchTask()
        {
            //
            // TODO: �ڴ˴���ӹ��캯���߼�
            //
        }
        /// <summary>
        /// ���ڴ����쳣
        /// </summary>
        public int errorkey = 1;
        override public object Work(params object[] args)
        {
            base.Work(args);
            for (int i = 0; i < 100; i++)
            {
                if (_taskState == TaskStatus.CancelPending)
                {
                    break;
                }
                if (errorkey == 0)
                {
                    errorkey = i / errorkey;
                }
                Thread thread = Thread.CurrentThread;
                if (thread != null)
                    Console.WriteLine("�̺߳�:[{0}],�߳�����:[{1}],�߳�״̬:[{2}],��ǰʱ��:[{3}],ѭ������:[{4}].", thread.Name, thread.GetHashCode(), "", DateTime.Now.ToLongTimeString(), i.ToString());
                else
                    Console.WriteLine("�̺߳�:[{0}],�߳�����:[{1}],�߳�״̬:[{2}],��ǰʱ��:[{3}],ѭ������:[{4}].", "", "", "", DateTime.Now.ToLongTimeString(), i.ToString());
                Thread.Sleep(100 * 1);
                this.ActivateProgressChangedEvent(i, i);
            }
            return 100;
        }
        public object Work2(params object[] args)
        {
            base.Work(args);
            for (int i = 0; i < 100; i++)
            {
                if (_taskState == TaskStatus.CancelPending)
                {
                    break;
                }
                if (errorkey == 0)
                {
                    errorkey = i / errorkey;
                }
                Thread thread = Thread.CurrentThread;
                if (thread != null)
                { Console.WriteLine("�̺߳�:[{0}],�߳�����:[{1}],�߳�״̬:[{2}],��ǰʱ��:[{3}],ѭ������:[{4}].", thread.Name, thread.GetHashCode(), "", DateTime.Now.ToLongTimeString(), i.ToString()); }
                else
                { Console.WriteLine("�̺߳�:[{0}],�߳�����:[{1}],�߳�״̬:[{2}],��ǰʱ��:[{3}],ѭ������:[{4}].", "", "", "", DateTime.Now.ToLongTimeString(), i.ToString()); }
                Thread.Sleep(100 * 1);
                this.ActivateProgressChangedEvent(i, i);
            }
            return 100;
        }
    }
}
