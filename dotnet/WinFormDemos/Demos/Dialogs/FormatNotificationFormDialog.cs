using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Demo
{
    public partial class FormatNotificationFormDialog : ModelessFormDialog
    {
        public FormatNotificationFormDialog()
        {
            InitializeComponent();
           
        }

        private void OnValidating(object sender, CancelEventArgs e)
        {
            string msg = string.Empty;

            TextBox txt = sender as TextBox;
            if (txt != null && txt.Text.Trim() == string.Empty)
            {
                msg = string.Format("{0} is Empty", lblApplication.Text);
                e.Cancel = true;
            }
            this.ctrErrorProvider.SetError(txt, msg);

        }
    }
}
