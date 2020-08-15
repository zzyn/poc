using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Drawing;
using System.IO;
using System.Reflection;
using System.Text;
using System.Windows.Forms;
using Common.BaseControl;
using Common.BaseUI;
using Common.Entity;
using Mono.Cecil;
using Mono.Cecil.Cil;
using Mono.Collections.Generic;
using System.Threading;

namespace BigFrame
{
    public partial class TreeMenuForm : BaseDesignForm
    {


        public Dictionary<string, BusinessEntity> MenuInfo { get; private set; }

        public DockingTab DockingTab { get; private set; }

        public TreeMenuForm(DockingTab docTab)
        {
            this.DockingTab = docTab;
            InitializeComponent();
            InitTreeMenu();
        }

        private void InitTreeMenu()
        {
            MenuInfo = this.GetBusinessTreeMenu();
            this.ilMenu.Images.Clear();
            this.tvMenu.Nodes.Clear();

            foreach (KeyValuePair<string, BusinessEntity> node in MenuInfo)
            {
                BusinessEntity entity = node.Value;

                this.ilMenu.Images.Add(entity.TabPageName, entity.Icon);
                this.tvMenu.AddNode(null, entity.TabPageName, entity.Icon, entity.TabPageName, 0, Color.Black, Color.LightSkyBlue, false);

            }
        }

        private Dictionary<string, BusinessEntity> GetBusinessTreeMenu()
        {
            Dictionary<string, BusinessEntity> result = new Dictionary<string, BusinessEntity>();

            DirectoryInfo di = new DirectoryInfo(ClientConfig.BusinessPath);
            if (di.Exists)
            {

                FileInfo[] files = di.GetFiles();
                foreach (FileInfo file in files)
                {
                    if (file.Extension.Contains("dll")
                       || file.Extension.Contains("exe"))
                    {
                        string classFullName = this.GetAssemblyMainFormClassFullName(file.FullName);

                        if (classFullName != string.Empty)
                        {

                            Assembly assembly = Assembly.LoadFile(file.FullName);
                            Form mainForm = assembly.CreateInstance(classFullName) as Form;

                            string title = file.Name;
                            title = title.Substring(0, title.Length - file.Extension.Length);

                            result.Add(title, new BusinessEntity
                            {
                                AssemblyFullName = file.FullName,
                                AssemblyName = file.Name,
                                TabPageName = title,
                                ClassFullName = classFullName,
                                Icon = mainForm.Icon.ToBitmap()
                            });
                        }
                    }
                }
            }

            return result;
        }

        private string GetAssemblyMainFormClassFullName(string AssemblyPath)
        {
            string classFullName = string.Empty;
            Assembly assembly = Assembly.LoadFile(AssemblyPath);
            AssemblyDefinition ad = AssemblyDefinition.ReadAssembly(AssemblyPath);

            TypeDefinition tdMainForm = null;

            foreach (TypeDefinition type in ad.MainModule.Types)
            {
                if (type.IsClass
                    && type.BaseType != null
                    &&
                     (type.BaseType.FullName == typeof(Form).FullName
                      || assembly.GetType(type.FullName).IsSubclassOf(typeof(Form))
                      )
                    )
                {
                    if (ad.EntryPoint != null)
                    {
                        Collection<Instruction> mainCode = ad.EntryPoint.Body.Method.Body.Instructions;

                        foreach (Instruction IL in mainCode)
                        {
                            if (IL.OpCode.Name == "newobj"
                                && IL.Operand != null
                                && IL.Operand.ToString().Contains(type.FullName))
                            {
                                tdMainForm = type;
                            }
                        }
                    }
                    else
                    {
                        if (type.Name == "MainForm")
                        {
                            tdMainForm = type;
                        }
                    }
                }
            }

            if (tdMainForm != null) 
            {
                classFullName = tdMainForm.FullName;
            }

            return classFullName;
        }

        private void OnTreeNodeMouseDoubleClick(object sender, TreeNodeMouseClickEventArgs e)
        {
            
            BusinessEntity entity = this.MenuInfo[e.Node.Text];

            Assembly ass = Assembly.LoadFile(entity.AssemblyFullName);

            Form dForm = ass.CreateInstance(entity.ClassFullName) as Form;
            dForm.TopLevel = false;
            dForm.FormBorderStyle = FormBorderStyle.None;
            dForm.BackColor = Color.White;
            dForm.Dock = DockStyle.Fill;

            DockingTabPage dPage = new DockingTabPage(entity.TabPageName);
            

            dPage.Controls.Add(dForm);
            this.DockingTab.Controls.Add(dPage);

            this.DockingTab.SelectedTabControl = dPage;
        }
    }
}
