using System.Configuration;

namespace Common
{
    public class CommonConst
    {
        public struct TableNames
        {
            
            public static string Match = "Match";

        }

        public struct FieldName
        {
            /// <summary>
            /// 豆瓣图书ID
            /// </summary>
            public static string BookID = "BookID";

            /// <summary>
            /// 书名/标题
            /// </summary>
            public static string BookName = "BookName";

            /// <summary>
            /// 副标题
            /// </summary>
            public static string SubTitle = "SubTitle";

            /// <summary>
            /// ISBN
            /// </summary>
            public static string ISBN = "ISBN";

            /// <summary>
            /// 作者
            /// </summary>
            public static string Author = "Author";

            /// <summary>
            /// 原作名
            /// </summary>
            public static string OriBookName = "OriBookName";

            /// <summary>
            /// 译者
            /// </summary>
            public static string Translater = "Translater";

            /// <summary>
            /// 出版社
            /// </summary>
            public static string Publisher = "Publisher";

            /// <summary>
            /// 出版日期
            /// </summary>
            public static string PublishDate = "PublishDate";

            /// <summary>
            /// 类别
            /// </summary>
            public static string Category = "Category";

            /// <summary>
            /// 装帧
            /// </summary>
            public static string Style = "Style";
            
            /// <summary>
            /// 页数
            /// </summary>
            public static string Pages = "Pages";

            /// <summary>
            /// 字数
            /// </summary>
            public static string Words = "Words";

            /// <summary>
            /// 定价
            /// </summary>
            public static string Price = "Price";

            /// <summary>
            /// 内容简介
            /// </summary>
            public static string ContentOverview = "ContentOverview";

            /// <summary>
            /// 目录
            /// </summary>
            public static string ContentTable = "ContentTable";

            /// <summary>
            /// 作者简介
            /// </summary>
            public static string AuthorOverview = "AuthorOverview";



        }

        public struct AppSetting
        {
            public static string FileName = ConfigurationManager.AppSettings["FileName"];

            public static string GoogleSoaUrl = ConfigurationManager.AppSettings["GoogleSoaUrl"];

            public static string GoogleSoaAspxUrl = ConfigurationManager.AppSettings["GoogleSoaAspxUrl"];

            public static string CtripSoaUrl = ConfigurationManager.AppSettings["CtripSoaUrl"];

            public static string UserID = ConfigurationManager.AppSettings["DefaultUserID"];

            public static string TimeSpan = ConfigurationManager.AppSettings["TimeSpan"];
        }

      

    }


}
