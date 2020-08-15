using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using Common.BaseUI;

namespace ShortCut
{
    public partial class ButtonSettingDialog : BaseDialogForm
    {
        public ButtonSettingDialog()
        {
            InitializeComponent();  
            
        }

        public ButtonSettingDialog(ButtonSettingEntity btnSetting) 
        {
            InitializeComponent();

            
            this.ButtonSetting = btnSetting;

            this.numUpDownColumn.Value = this.ButtonSetting.ColumnCount;
            this.numUpDownRow.Value = this.ButtonSetting.RowCount;
            this.numUpDownWidth.Value = this.ButtonSetting.Width;
            this.numUpDownHeight.Value = this.ButtonSetting.Height;
        }

        public ButtonSettingEntity ButtonSetting { get; private set; }

        protected override void OnAcceptClick(object sender, EventArgs e)
        {
            this.ButtonSetting = 
                new ButtonSettingEntity 
                {
                     ColumnCount = int.Parse(this.numUpDownColumn.Value.ToString()),
                     RowCount = int.Parse(this.numUpDownRow.Value.ToString()),
                     Width = int.Parse(this.numUpDownWidth.Value.ToString()),
                     Height = int.Parse(this.numUpDownHeight.Value.ToString())
                };

            base.OnAcceptClick(this, e);
        }

        

       

        
    }
}
