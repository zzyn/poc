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
    public partial class ModelessFormDialog : Form
    {
        public ModelessFormDialog()
        {
            InitializeComponent();
            this.btnAccept.DialogResult = DialogResult.OK;
            this.btnCancle.DialogResult = DialogResult.Cancel;
            this.AcceptButton = this.btnAccept;
            this.CancelButton = this.btnCancle;
        }

        public event EventHandler Accept;
        public event EventHandler Cancle;

        private void btnAccept_Click(object sender, EventArgs e)
        {
            if (Accept != null) Accept(this, EventArgs.Empty);
        }

        private void btnClose_Click(object sender, EventArgs e)
        {
            if (Cancle != null) Cancle(this, EventArgs.Empty);
            this.Close();
        }
    }
}
