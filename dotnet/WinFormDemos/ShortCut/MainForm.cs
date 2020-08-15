using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.Runtime.InteropServices;
using Common.BaseUI;

namespace ShortCut
{
    public partial class MainForm : BaseForm
    {

        ProfileEntity data = new ProfileEntity();
        string filepath = Environment.CurrentDirectory + "\\Users\\" + Environment.UserName + "\\ProfileData.xml";

        public MainForm()
        {
            InitializeComponent();
            CustomInitializeComponent();

            
            data.ReadXmlProfile(filepath);
            this.InitializeButtonLayout(data);
            this.SetStyle(ControlStyles.ResizeRedraw, true);
            
            
        }

        private void CustomInitializeComponent()
        {
            this.MaxButtonVisable = false;

            Button btnButtonConfig = new Button();
            // 
            // btnButtonConfig
            // 
            btnButtonConfig.BackColor = System.Drawing.SystemColors.MenuHighlight;
            btnButtonConfig.BackgroundImage = global::Common.Properties.Resources.config;
            btnButtonConfig.FlatAppearance.BorderSize = 0;
            btnButtonConfig.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            btnButtonConfig.Font = new System.Drawing.Font("Microsoft Sans Serif", 5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(50)));
            btnButtonConfig.Location = new System.Drawing.Point(42, 4);
            btnButtonConfig.Margin = new System.Windows.Forms.Padding(2, 4, 2, 4);
            btnButtonConfig.Name = "btnButtonConfig";
            btnButtonConfig.Size = new System.Drawing.Size(16, 16);
            btnButtonConfig.TabIndex = 2;
            btnButtonConfig.TabStop = false;
            btnButtonConfig.UseCompatibleTextRendering = true;
            btnButtonConfig.UseVisualStyleBackColor = false;
            btnButtonConfig.Click += new EventHandler(OnShowButtonSettingForm);
            

            this.flpCaptionButton.Controls.Add(btnButtonConfig);

            this.SetCaptionButtonVisible(btnButtonConfig, true);

            Button btnSettingConfig = new Button();
            // 
            // btnSettingConfig
            // 
            btnSettingConfig.BackColor = System.Drawing.SystemColors.MenuHighlight;
            btnSettingConfig.BackgroundImage = global::Common.Properties.Resources.setting;
            btnSettingConfig.FlatAppearance.BorderSize = 0;
            btnSettingConfig.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            btnSettingConfig.Font = new System.Drawing.Font("Microsoft Sans Serif", 5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(50)));
            btnSettingConfig.Location = new System.Drawing.Point(22, 4);
            btnSettingConfig.Margin = new System.Windows.Forms.Padding(2, 4, 2, 4);
            btnSettingConfig.Name = "btnSettingConfig";
            btnSettingConfig.Size = new System.Drawing.Size(16, 16);
            btnSettingConfig.TabIndex = 4;
            btnSettingConfig.TabStop = false;
            btnSettingConfig.UseCompatibleTextRendering = true;
            btnSettingConfig.UseVisualStyleBackColor = false;

            this.flpCaptionButton.Controls.Add(btnSettingConfig);

            this.SetCaptionButtonVisible(btnSettingConfig, true);

            Button btnSkinConfig = new Button();

            // 
            // btnSkinConfig
            // 
            btnSkinConfig.BackColor = System.Drawing.SystemColors.MenuHighlight;
            btnSkinConfig.BackgroundImage = global::Common.Properties.Resources.magic;
            btnSkinConfig.FlatAppearance.BorderSize = 0;
            btnSkinConfig.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            btnSkinConfig.Font = new System.Drawing.Font("Microsoft Sans Serif", 5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Pixel, ((byte)(50)));
            btnSkinConfig.Location = new System.Drawing.Point(2, 4);
            btnSkinConfig.Margin = new System.Windows.Forms.Padding(2, 4, 2, 4);
            btnSkinConfig.Name = "btnSkinConfig";
            btnSkinConfig.Size = new System.Drawing.Size(16, 16);
            btnSkinConfig.TabIndex = 1;
            btnSkinConfig.TabStop = false;
            btnSkinConfig.UseCompatibleTextRendering = true;
            btnSkinConfig.UseVisualStyleBackColor = false;
            btnSkinConfig.Click += new EventHandler(OnDockToDesktop);
            
            
            this.flpCaptionButton.Controls.Add(btnSkinConfig);

            this.SetCaptionButtonVisible(btnSkinConfig, true);

            this.flpCaptionButton.Controls.SetChildIndex(btnSkinConfig, 0);
            this.flpCaptionButton.Controls.SetChildIndex(btnSettingConfig, 1);
            this.flpCaptionButton.Controls.SetChildIndex(btnButtonConfig, 2);
            this.flpCaptionButton.Controls.SetChildIndex(this.btnMin, 3);
            this.flpCaptionButton.Controls.SetChildIndex(this.btnMax, 4);
            this.flpCaptionButton.Controls.SetChildIndex(this.btnClose,5);
        }

        bool bRegisterBar = false;
        private void OnDockToDesktop(object sender, EventArgs e)
        {
            
            API.AppBar.RegisterBar(this.Handle, this.Size.Width, this.Size.Height, ref this.bRegisterBar, ref this.uCallBack);
            if (!bRegisterBar) 
            {
                this.InitializeButtonLayout(data);
                this.SuspendLayout();
            }
        }

        private void InitializeButtonLayout(ProfileEntity data)
        {
            int RowCount = data.ButtonSetting.RowCount;
            int ColumnCount = data.ButtonSetting.ColumnCount;
            int TotalCount = RowCount * ColumnCount;
            int ButtonHeight = data.ButtonSetting.Height;
            int ButtonWidth = data.ButtonSetting.Width;
            int Padding = 1;
            int PageIndex = 0;
            this.flpContent.Controls.Clear();

            for (int i = 0; i < TotalCount; i++)
            {
                int ColumnIndex = i % ColumnCount;
                int RowIndex = i / ColumnCount;
                Button btnButton = new Button();
                btnButton.Name = "btn_Page_" + PageIndex + "_Row_" + RowIndex + "_Column_" + ColumnIndex;

                btnButton.Height = ButtonHeight;
                btnButton.Width = ButtonWidth;
                btnButton.Margin = new System.Windows.Forms.Padding(Padding);
                btnButton.UseVisualStyleBackColor = false;

                btnButton.AllowDrop = true;
                btnButton.FlatStyle = FlatStyle.Flat;
                btnButton.FlatAppearance.BorderSize = 0;
                btnButton.BackColor = SystemColors.Info;
                btnButton.DragDrop += new DragEventHandler(OnDragDrop);
                btnButton.DragEnter += new DragEventHandler(OnDragEnter);
                btnButton.MouseHover += new EventHandler(OnMouseHover);
                btnButton.ContextMenuStrip = ctrButtonMenuContext;
                


                this.flpContent.Controls.Add(btnButton);
            }
            this.Width = (ButtonWidth + Padding * 2) * ColumnCount;
            this.Height = (ButtonHeight + Padding * 2) * RowCount + this.pnlCaption.Height + this.flpTabs.Height;
        }

        private void OnMouseHover(object sender, EventArgs e)
        {
            Button button = sender as Button;
            ToolTip btnTips = new ToolTip();

            btnTips.SetToolTip(button, "");
            btnTips.ShowAlways = true;
            btnTips.Show(button.Name, this,button.Location.X, button.Location.Y, 500);
            
            
        }

        private void OnDragLeave(object sender, DragEventArgs e)
        {

        }

        private void OnDragOver(object sender, DragEventArgs e)
        {

        }

        private void OnDragEnter(object sender, DragEventArgs e)
        {
            if (e.Data.GetDataPresent(DataFormats.FileDrop))
            {
                e.Effect = DragDropEffects.Copy;
            }
        }

        private void OnDragDrop(object sender, DragEventArgs e)
        {
            string[] files = (string[])e.Data.GetData(DataFormats.FileDrop);
            foreach (string file in files)
            {

                if (System.IO.File.Exists(file))
                {
                    //((Button)sender).Image = Icon.ExtractAssociatedIcon(file).ToBitmap();

                    ((Button)sender).Image = API.FileIcon.GetFileIcon(file, API.FileIcon.IconSize.Large).ToBitmap();
                }
                else
                {
                    ((Button)sender).Image = API.FileIcon.GetFolderIcon(file, API.FileIcon.IconSize.Large).ToBitmap();
                }


            }

        }

        private void OnShowButtonSettingForm(object sender, EventArgs e)
        {
            ButtonSettingDialog frm = new ButtonSettingDialog(data.ButtonSetting);
            frm.OnAccept += new EventHandler(OnAcceptButtonSettingForm);
            frm.Show();
        }

        private void OnAcceptButtonSettingForm(object sender, EventArgs e)
        {
            ButtonSettingDialog frm = sender as ButtonSettingDialog;
            data.ButtonSetting = frm.ButtonSetting;   
            this.InitializeButtonLayout(data);
            data.SaveXmlProfile(filepath);
            frm.Close();

        }

        private void OnShowButtonAttributeForm(object sender, EventArgs e)
        {
            ButtonAttributeDialog frm = new ButtonAttributeDialog();
            frm.Show();
        }

        private void OnShow(object sender, EventArgs e)
        {
            this.WindowState = FormWindowState.Normal;
            this.tsmiButtonMenuShow.Checked = true;
            this.tsmiNotifyMenuShow.Checked = true;
        }

        private void OnLoad(object sender, EventArgs e)
        {
            API.SetParent(this.Handle.ToInt32(), API.FindWindow("MyShortCut.MainForm", this.lblTitle.Text));
            Rectangle ScreenArea = Screen.GetWorkingArea(this);
        }

        #region Application Bar
        protected override void WndProc(ref System.Windows.Forms.Message m)
        {
            if (m.Msg == uCallBack)
            {
                //switch (m.WParam.ToInt32())
                //{
                //    case (int)API.AppBar.ABNotify.ABN_POSCHANGED:
                //        API.AppBar.ABSetPos(this.Handle, this.Size.Width, this.Size.Height);
                //        break;
                //}
                API.AppBar.ABSetPos(this.Handle, this.Size.Width, this.Size.Height);
            }
            
            base.WndProc(ref m);
        }

       

        private int uCallBack;
        
        #endregion

        private void MainForm_ResizeEnd(object sender, EventArgs e)
        {

        }

        private void MainForm_ResizeBegin(object sender, EventArgs e)
        {

        }

        private void MainForm_Resize(object sender, EventArgs e)
        {

        }

        private void MainForm_Paint(object sender, PaintEventArgs e)
        {

        }

    }
}
