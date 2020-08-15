using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Text;
using Common.BaseControl;

namespace Common.Entity
{
    public class BusinessEntity : ICloneable
    {
        public string AssemblyName { get; set; }

        public string AssemblyFullName { get; set; }

        public Type AssemblyType { get; set; }

        public string BusinessID { get; set; }

        public string BusinessName { get; set; }

        public string ClassFullName { get; set; }

        public string DownloadGroupName { get; set; }

        public string TabPageName { get; set; }

        public DockingTab CurrentDockingTab { get; set; }

        public DockingTabPage CurrentDockingTabPage { get; set; }

        public DockingTabPage FromDockingTabPage { get; set; }

        public Image Icon { get; set; }

        [DefaultValue(true)]
        public bool IsDesidable { get; set; }

        [DefaultValue(true)]
        public bool IsTab { get; set; }

        [DefaultValue(false)]
        public bool IsDialog { get; set; }

        public object Clone()
        {
            return MemberwiseClone();
        }

        public void Dispose()
        {
            this.CurrentDockingTab = null;
            this.CurrentDockingTabPage = null;
            this.FromDockingTabPage = null;
            this.AssemblyType = null;
            if (this.Icon != null)
            {
                this.Icon.Dispose();
                this.Icon = null;
            }

        }
    }
}
