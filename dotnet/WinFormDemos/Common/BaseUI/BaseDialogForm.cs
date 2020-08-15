using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Common.BaseUI
{
    public partial class BaseDialogForm : BaseForm
    {
        public BaseDialogForm()
        {
            InitializeComponent();
            this.MaxButtonVisable = false;
            this.MinButtonVisable = false;
            
        }

        public event EventHandler OnAccept;

        public event EventHandler OnCancle;

        protected virtual void OnAcceptClick(object sender, EventArgs e)
        {
            if(OnAccept != null) OnAccept(this, EventArgs.Empty);
            this.Close();

        }

        protected virtual void OnCancleClick(object sender, EventArgs e)
        {
            if (OnCancle != null) OnCancle(this, EventArgs.Empty);
            this.Close();
        }

        
    }
}
