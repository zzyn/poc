using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace BigFrame
{
    public partial class DefaultForm : Common.BaseUI.BaseDesignForm
    {
        public DefaultForm()
        {
            InitializeComponent();
            qriCopyright.DarkBrush = new SolidBrush(Color.White);
            qriCopyright.LightBrush = new SolidBrush(Color.Black);
            qriCopyright.Text = "Made in China";
            this.picVersion.Image = Common.Properties.Resources.logo128;
            this.picAuthor.Image = BigFrame.Properties.Resources.face100;
        }
    }
}
