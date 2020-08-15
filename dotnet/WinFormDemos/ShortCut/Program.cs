using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.IO;

namespace ShortCut
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            string ProfilesPath = Environment.CurrentDirectory + "\\Users";
            string UserProfilePath = ProfilesPath+ "\\" + Environment.UserName;
            if (!Directory.Exists(ProfilesPath))
            {
                Directory.CreateDirectory(ProfilesPath); 
            }

            if (!Directory.Exists(UserProfilePath)) 
            {
                Directory.CreateDirectory(UserProfilePath); 
            }


            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new MainForm());
        }
    }
}
