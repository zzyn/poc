using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using Newtonsoft;
using Newtonsoft.Json;
using System.Xml;

namespace ShortCut
{

    public class ButtonSettingEntity 
    {
        public int ColumnCount { get; set; }
        public int RowCount { get; set; }

        public int Height { get; set; }
        public int Width { get; set; }
    }

    public class ButtonEntity 
    {
        public int ColumnIndex { get; set; }
        public int RowIndex { get; set; }
        public int PageIndex { get; set; }
        public string DataPath { get; set; }
        public string DataName { get; set; }
        public string DataComment { get; set; }
        public string IconPath { get; set; }
        public int IconNumber { get; set; }
        public int Priorty { get; set; }
        public int Usage { get; set; }
        public string Type { get; set; }
        
    }

    public class PageEntity 
    {
        public string Name { get; set; }

        public int Index { get; set; }
    }

    public class ProfileEntity
    {
        public List<ButtonEntity> ButtonData { get; set; }

        public List<PageEntity> PageData { get; set; }

        public ButtonSettingEntity ButtonSetting { get; set; }

        public void ReadJsonProfile(string filePath) 
        {
            using (JsonTextReader reader = new JsonTextReader(new StreamReader(filePath, Encoding.Unicode))) 
            {
                
            }
            
        }

        public void SaveJsonProfile(string filePath) 
        {

        }

        public void ReadXmlProfile(string filePath) 
        {
            XmlDocument xd = new XmlDocument();
            try
            {
                this.PageData = new List<PageEntity>();
                this.ButtonData = new List<ButtonEntity>();

                if (File.Exists(filePath)) 
                {
                    xd.Load(filePath);
                }
                
                XmlNodeList pageNodes = xd.SelectNodes("/Profile/PageData/Page");
                XmlNodeList btnNodes = xd.SelectNodes("/Profile/ButtonData/Button");
                XmlNode btnSettingNode = xd.SelectSingleNode("/Profile/ButtonSetting");


                if (pageNodes != null) 
                {
                    foreach (XmlNode node in pageNodes)
                    {
                        string Name = GetSingleNodeInnerText(node, "./Name");
                        int Index = 0;
                        int.TryParse(GetSingleNodeInnerText(node, "./Index"), out Index);
                        this.PageData.Add(
                            new PageEntity
                            {
                                Name = Name,
                                Index = Index
                            }
                            );
                    }
                }


                if (btnNodes != null) 
                {
                    foreach (XmlNode node in btnNodes)
                    {
                        string DataPath = GetSingleNodeInnerText(node, "./DataPath");
                        string DataName = GetSingleNodeInnerText(node, "./DataName");

                        int PageIndex = 0;
                        int.TryParse(GetSingleNodeInnerText(node, "./PageIndex"), out PageIndex);
                        int ColumnIndex = 0;
                        int.TryParse(GetSingleNodeInnerText(node, "./ColumnIndex"), out ColumnIndex);
                        int RowIndex = 0;
                        int.TryParse(GetSingleNodeInnerText(node, "./RowIndex"), out RowIndex);
                        this.ButtonData.Add(
                            new ButtonEntity
                            {
                                PageIndex = PageIndex,
                                ColumnIndex = ColumnIndex,
                                RowIndex = RowIndex,
                                DataPath = DataPath,
                                DataName = DataName
                            });
                    }
                }
               
                int ColumnCount = 10;
                int RowCount = 10;
                int Height = 32;
                int Width = 32;

                if (btnSettingNode != null) 
                {
                    int.TryParse(GetSingleNodeInnerText(btnSettingNode, "./ColumnCount"), out ColumnCount);
                    int.TryParse(GetSingleNodeInnerText(btnSettingNode, "./RowCount"), out RowCount);
                    int.TryParse(GetSingleNodeInnerText(btnSettingNode, "./Height"), out Height);
                    int.TryParse(GetSingleNodeInnerText(btnSettingNode, "./Width"), out Width);
                }
                
                this.ButtonSetting = new ButtonSettingEntity 
                {
                    ColumnCount = ColumnCount,
                    RowCount = RowCount,
                    Height = Height,
                    Width = Width,
                };
            }
            catch (XmlException exp)
            {
                throw exp;
            }
            catch (Exception exp)
            {
                throw exp;
            }
            
            
        }

        public void SaveXmlProfile(string filePath)
        {
            XmlDocument xd = new XmlDocument();
            try
            {
                XmlNode Root = xd.CreateElement("Profile");
                XmlNode PageData = xd.CreateElement("PageData");
                foreach (PageEntity pe in this.PageData) 
                {
                    XmlNode Page = xd.CreateElement("Page");
                    XmlNode Name = xd.CreateElement("Name");
                    XmlNode Index = xd.CreateElement("Index");
                    Name.InnerText = pe.Name;
                    Index.InnerText = pe.Index.ToString();
                    Page.AppendChild(Name);
                    Page.AppendChild(Index);
                    PageData.AppendChild(Page);
                }
                Root.AppendChild(PageData);


                XmlNode ButtonData = xd.CreateElement("ButtonData");
                foreach (ButtonEntity be in this.ButtonData)
                {
                    XmlNode Button = xd.CreateElement("Button");
                    XmlNode PageIndex = xd.CreateElement("PageIndex");
                    XmlNode ColumnIndex = xd.CreateElement("ColumnIndex");
                    XmlNode RowIndex = xd.CreateElement("RowIndex");
                    XmlNode DataPath = xd.CreateElement("DataPath");

                    PageIndex.InnerText = be.PageIndex.ToString();
                    ColumnIndex.InnerText = be.ColumnIndex.ToString();
                    RowIndex.InnerText = be.RowIndex.ToString();
                    DataPath.InnerText = be.DataPath;

                    Button.AppendChild(PageIndex);
                    Button.AppendChild(ColumnIndex);
                    Button.AppendChild(RowIndex);
                    Button.AppendChild(DataPath);

                    ButtonData.AppendChild(Button);
                }
                Root.AppendChild(ButtonData);

                XmlNode ButtonSetting = xd.CreateElement("ButtonSetting");
                XmlNode ColumnCount = xd.CreateElement("ColumnCount");
                XmlNode RowCount = xd.CreateElement("RowCount");
                XmlNode ButtonWidth = xd.CreateElement("Width");
                XmlNode ButtonHeight = xd.CreateElement("Height");
                ColumnCount.InnerText = this.ButtonSetting.ColumnCount.ToString();
                RowCount.InnerText = this.ButtonSetting.RowCount.ToString();
                ButtonHeight.InnerText = this.ButtonSetting.Height.ToString();
                ButtonWidth.InnerText = this.ButtonSetting.Width.ToString();
                ButtonSetting.AppendChild(ColumnCount);
                ButtonSetting.AppendChild(RowCount);
                ButtonSetting.AppendChild(ButtonHeight);
                ButtonSetting.AppendChild(ButtonWidth);
                Root.AppendChild(ButtonSetting);

                xd.AppendChild(Root);

                xd.Save(filePath);
            }
            catch(Exception exp)
            {
                throw exp;
            }
        }

        private string GetSingleNodeInnerText(XmlNode node, string XPath) 
        {
            string nResult = string.Empty;

            if (node == null) return nResult;

            XmlNode xn = node.SelectSingleNode(XPath);

            if (xn == null) return nResult;


            nResult = xn.InnerText;

            return nResult;

        }
      
    }
}
