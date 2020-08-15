using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace BigFrame
{
    public partial class FavoriteInfoForm : Form, ILeftToolForm
    {
        public FavoriteInfoForm()
        {
            InitializeComponent();
            this.TopLevel = false;
        }

        public Common.CustomControl.CmTreeView MainControl
        {
            get { return null; }
        }

        public void InitMainControl()
        {

        }
    }
}
