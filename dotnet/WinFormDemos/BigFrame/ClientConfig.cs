using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Configuration;

namespace BigFrame
{
    public static class ClientConfig
    {
        public static  string BusinessPath = string.Format("{0}\\{1}",Environment.CurrentDirectory ,ConfigurationManager.AppSettings["BusinessPath"]);

        public static  string LogPath = string.Format("{0}\\{1}", Environment.CurrentDirectory, ConfigurationManager.AppSettings["LogPath"]);

        public static  string ProfilePath = string.Format("{0}\\{1}", Environment.CurrentDirectory, ConfigurationManager.AppSettings["ProfilePath"]);
    }
}
