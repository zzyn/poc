using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using Common;
using System.IO;
using System.Xml;
using System.Threading;
using System.Net;

namespace AsynchThread
{
    /// <summary>
    /// 匹配两份价格数据
    /// </summary>
    class MatchAsyncTask : Task
    {

        /// <summary>
        /// 用于触发异常
        /// </summary>
        public int errorkey = 1;

        public override object Work(params object[] args)
        {
            base.Work(args);

            MatchInputEntity input = new MatchInputEntity();

            if (args.Length == 1)
            {
                if (args[0] is MatchInputEntity)
                {
                    input = args[0] as MatchInputEntity;
                }
            }
            else
            {
                return 0;
            }

            string baseUrl = input.Url;

            long BookID = input.BookID;

            while (true)
            {

                try
                {

                    #region request single book page
                    string bookUrl = baseUrl + BookID.ToString();
                    string response = string.Empty;
                    using (WebClient client = new WebClient()) 
                    {
                        client.Encoding = Encoding.UTF8;
                        response = client.DownloadString(bookUrl);
                    }
                    #endregion

                    #region filter not book page
                    bool IsFiltered = true;
                    StringReader srResponse = new StringReader(response); 
                    {
                        bool flag = true;
                        while (flag)
                        {
                            string line = srResponse.ReadLine();
                            if (line.Contains("<html"))
                            {

                                if (line.Contains("book-new-nav")) 
                                {
                                    IsFiltered = false;
                                }
                                flag = false;
                            }
                        }
                    }
                    #endregion
                    StringBuilder xmlContent = new StringBuilder();

                    #region book info
                    if (!IsFiltered)
                    {
                        #region remove not xml element
                        StringReader sr = new StringReader(response);
                        
                        bool flag = true;
                        int no = 1;
                        int script = 0;
                        int style = 0;
                        while (flag)
                        {
                                
                            string line = sr.ReadLine();
                            if (line == null) 
                            {
                                break;
                            }

                            if (!(line.Contains("<!DOCTYPE")
                                    || line.Contains("<meta")
                                    || line.Contains("<link")
                                    || (line.Trim() == string.Empty)
                                    )
                                )
                            {

                                if (line.Contains("<script"))
                                {
                                    script = no;
                                }
                                else if (line.Contains("</script"))
                                {
                                    script = 0;
                                }
                                else if (line.Contains("<style"))
                                {
                                    script = no;
                                }
                                else if (line.Contains("</style"))
                                {
                                    style = 0;
                                }
                                else if (line.Contains("</html>"))
                                {
                                    xmlContent.Append(line);
                                    flag = false;
                                }

                                if (script != 0 && no > script)
                                {

                                }
                                else if (line.Contains("<script") && line.Contains("</script")) 
                                {

                                }
                                else if (style != 0 && no > style) 
                                {

                                }
                                else if (line.Contains("<style") && line.Contains("</style")) 
                                {

                                }
                                else
                                {
                                    if (line.Contains("<img"))
                                    {
                                        int index = line.IndexOf("<img");
                                        string segment = string.Empty;
                                        string[] array = line.Split(new string[] { ">" }, StringSplitOptions.RemoveEmptyEntries);
                                        foreach (string item in array) 
                                        {
                                            if (item.Contains("<img")) 
                                            {
                                                if (item[item.Length - 1] != '/')
                                                {
                                                    line.Replace(item + ">", item + "/>");
                                                   
                                                }
                                              
                                                
                                            }
                                        }
                                        xmlContent.AppendLine(line + "</img>");
                                    }
                                    else 
                                    {
                                        xmlContent.AppendLine(line);
                                    }
                                    
                                }
                                
                            }
                            
                            no++;
                            
                        }

                        //sr.Close();

                        #endregion

                        #region convert to xml document
                        XmlDocument xd = new XmlDocument();
                        xd.Load(new StringReader(xmlContent.ToString()));
                        

                        #endregion

                        #region get the book info
                        #endregion
                    }
                    #endregion

                   

                }
                catch (WebException exp)
                {

                }
                catch (XmlException exp)
                {

                }
                catch (Exception exp)
                {

                }

                BookID++;
            }

            return 100;
        }
    }
}
