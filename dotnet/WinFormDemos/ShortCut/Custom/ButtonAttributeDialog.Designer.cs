namespace ShortCut
{
    partial class ButtonAttributeDialog
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.flpBottom = new System.Windows.Forms.FlowLayoutPanel();
            this.flpTop = new System.Windows.Forms.FlowLayoutPanel();
            this.lblButtonType = new System.Windows.Forms.Label();
            this.cmbType = new System.Windows.Forms.ComboBox();
            this.chkTypeEnable = new System.Windows.Forms.CheckBox();
            this.lblName = new System.Windows.Forms.Label();
            this.txtName = new System.Windows.Forms.TextBox();
            this.lblLocation = new System.Windows.Forms.Label();
            this.txtLocation = new System.Windows.Forms.TextBox();
            this.btnLocation = new System.Windows.Forms.Button();
            this.lblWorkplace = new System.Windows.Forms.Label();
            this.txtWorkplace = new System.Windows.Forms.TextBox();
            this.lblPriorty = new System.Windows.Forms.Label();
            this.cmbPriorty = new System.Windows.Forms.ComboBox();
            this.lblComment = new System.Windows.Forms.Label();
            this.txtComment = new System.Windows.Forms.TextBox();
            this.chkUseCustomIcon = new System.Windows.Forms.CheckBox();
            this.lblIconLocation = new System.Windows.Forms.Label();
            this.txtIconLocation = new System.Windows.Forms.TextBox();
            this.btnIconLocation = new System.Windows.Forms.Button();
            this.lblIconNumber = new System.Windows.Forms.Label();
            this.numUpDownIconNumber = new System.Windows.Forms.NumericUpDown();
            this.btnIconNumber = new System.Windows.Forms.Button();
            this.lblShow = new System.Windows.Forms.Label();
            this.cmbShow = new System.Windows.Forms.ComboBox();
            this.chkShowFolderContent = new System.Windows.Forms.CheckBox();
            this.btnShowFolderContent = new System.Windows.Forms.Button();
            this.chkMouseHook = new System.Windows.Forms.CheckBox();
            this.flpLeft = new System.Windows.Forms.FlowLayoutPanel();
            this.gbIcon = new System.Windows.Forms.GroupBox();
            this.gbUsage = new System.Windows.Forms.GroupBox();
            this.pnlContent.SuspendLayout();
            this.flpTop.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownIconNumber)).BeginInit();
            this.flpLeft.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlBottom
            // 
            this.pnlBottom.Location = new System.Drawing.Point(0, 475);
            this.pnlBottom.Size = new System.Drawing.Size(450, 25);
            // 
            // pnlContent
            // 
            this.pnlContent.Controls.Add(this.flpTop);
            this.pnlContent.Controls.Add(this.flpLeft);
            this.pnlContent.Controls.Add(this.flpBottom);
            this.pnlContent.Size = new System.Drawing.Size(450, 450);
            // 
            // pnlCaption
            // 
            this.pnlCaption.Size = new System.Drawing.Size(450, 25);
            // 
            // flpBottom
            // 
            this.flpBottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.flpBottom.Location = new System.Drawing.Point(0, 350);
            this.flpBottom.Margin = new System.Windows.Forms.Padding(4);
            this.flpBottom.Name = "flpBottom";
            this.flpBottom.Size = new System.Drawing.Size(450, 100);
            this.flpBottom.TabIndex = 0;
            // 
            // flpTop
            // 
            this.flpTop.Controls.Add(this.lblButtonType);
            this.flpTop.Controls.Add(this.cmbType);
            this.flpTop.Controls.Add(this.chkTypeEnable);
            this.flpTop.Controls.Add(this.lblName);
            this.flpTop.Controls.Add(this.txtName);
            this.flpTop.Controls.Add(this.lblLocation);
            this.flpTop.Controls.Add(this.txtLocation);
            this.flpTop.Controls.Add(this.btnLocation);
            this.flpTop.Controls.Add(this.lblWorkplace);
            this.flpTop.Controls.Add(this.txtWorkplace);
            this.flpTop.Controls.Add(this.lblPriorty);
            this.flpTop.Controls.Add(this.cmbPriorty);
            this.flpTop.Controls.Add(this.lblComment);
            this.flpTop.Controls.Add(this.txtComment);
            this.flpTop.Controls.Add(this.chkUseCustomIcon);
            this.flpTop.Controls.Add(this.lblIconLocation);
            this.flpTop.Controls.Add(this.txtIconLocation);
            this.flpTop.Controls.Add(this.btnIconLocation);
            this.flpTop.Controls.Add(this.lblIconNumber);
            this.flpTop.Controls.Add(this.numUpDownIconNumber);
            this.flpTop.Controls.Add(this.btnIconNumber);
            this.flpTop.Controls.Add(this.lblShow);
            this.flpTop.Controls.Add(this.cmbShow);
            this.flpTop.Controls.Add(this.chkShowFolderContent);
            this.flpTop.Controls.Add(this.btnShowFolderContent);
            this.flpTop.Controls.Add(this.chkMouseHook);
            this.flpTop.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpTop.Location = new System.Drawing.Point(75, 0);
            this.flpTop.Margin = new System.Windows.Forms.Padding(4);
            this.flpTop.Name = "flpTop";
            this.flpTop.Size = new System.Drawing.Size(375, 350);
            this.flpTop.TabIndex = 1;
            // 
            // lblButtonType
            // 
            this.lblButtonType.AutoSize = true;
            this.lblButtonType.Location = new System.Drawing.Point(49, 8);
            this.lblButtonType.Margin = new System.Windows.Forms.Padding(49, 8, 8, 8);
            this.lblButtonType.Name = "lblButtonType";
            this.lblButtonType.Size = new System.Drawing.Size(31, 13);
            this.lblButtonType.TabIndex = 0;
            this.lblButtonType.Text = "Type";
            // 
            // cmbType
            // 
            this.cmbType.BackColor = System.Drawing.SystemColors.Info;
            this.cmbType.DropDownWidth = 121;
            this.cmbType.FormattingEnabled = true;
            this.cmbType.Location = new System.Drawing.Point(92, 4);
            this.cmbType.Margin = new System.Windows.Forms.Padding(4);
            this.cmbType.Name = "cmbType";
            this.cmbType.Size = new System.Drawing.Size(200, 21);
            this.cmbType.TabIndex = 20;
            // 
            // chkTypeEnable
            // 
            this.chkTypeEnable.AutoSize = true;
            this.flpTop.SetFlowBreak(this.chkTypeEnable, true);
            this.chkTypeEnable.Location = new System.Drawing.Point(304, 8);
            this.chkTypeEnable.Margin = new System.Windows.Forms.Padding(8);
            this.chkTypeEnable.Name = "chkTypeEnable";
            this.chkTypeEnable.Size = new System.Drawing.Size(59, 17);
            this.chkTypeEnable.TabIndex = 16;
            this.chkTypeEnable.Text = "Enable";
            this.chkTypeEnable.UseVisualStyleBackColor = true;
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Location = new System.Drawing.Point(45, 41);
            this.lblName.Margin = new System.Windows.Forms.Padding(45, 8, 8, 8);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(35, 13);
            this.lblName.TabIndex = 1;
            this.lblName.Text = "Name";
            // 
            // txtName
            // 
            this.flpTop.SetFlowBreak(this.txtName, true);
            this.txtName.Location = new System.Drawing.Point(92, 37);
            this.txtName.Margin = new System.Windows.Forms.Padding(4);
            this.txtName.Name = "txtName";
            this.txtName.Size = new System.Drawing.Size(200, 20);
            this.txtName.TabIndex = 10;
            // 
            // lblLocation
            // 
            this.lblLocation.AutoSize = true;
            this.lblLocation.Location = new System.Drawing.Point(13, 70);
            this.lblLocation.Margin = new System.Windows.Forms.Padding(13, 8, 8, 8);
            this.lblLocation.Name = "lblLocation";
            this.lblLocation.Size = new System.Drawing.Size(67, 13);
            this.lblLocation.TabIndex = 2;
            this.lblLocation.Text = "File Location";
            // 
            // txtLocation
            // 
            this.txtLocation.Location = new System.Drawing.Point(92, 66);
            this.txtLocation.Margin = new System.Windows.Forms.Padding(4);
            this.txtLocation.Name = "txtLocation";
            this.txtLocation.Size = new System.Drawing.Size(200, 20);
            this.txtLocation.TabIndex = 11;
            // 
            // btnLocation
            // 
            this.btnLocation.FlatAppearance.BorderSize = 0;
            this.flpTop.SetFlowBreak(this.btnLocation, true);
            this.btnLocation.Location = new System.Drawing.Point(304, 66);
            this.btnLocation.Margin = new System.Windows.Forms.Padding(8, 4, 4, 4);
            this.btnLocation.Name = "btnLocation";
            this.btnLocation.Size = new System.Drawing.Size(60, 20);
            this.btnLocation.TabIndex = 24;
            this.btnLocation.Text = "Browse";
            this.btnLocation.UseVisualStyleBackColor = false;
            // 
            // lblWorkplace
            // 
            this.lblWorkplace.AutoSize = true;
            this.lblWorkplace.Location = new System.Drawing.Point(21, 99);
            this.lblWorkplace.Margin = new System.Windows.Forms.Padding(21, 8, 8, 8);
            this.lblWorkplace.Name = "lblWorkplace";
            this.lblWorkplace.Size = new System.Drawing.Size(59, 13);
            this.lblWorkplace.TabIndex = 3;
            this.lblWorkplace.Text = "Workplace";
            // 
            // txtWorkplace
            // 
            this.flpTop.SetFlowBreak(this.txtWorkplace, true);
            this.txtWorkplace.Location = new System.Drawing.Point(92, 95);
            this.txtWorkplace.Margin = new System.Windows.Forms.Padding(4);
            this.txtWorkplace.Name = "txtWorkplace";
            this.txtWorkplace.Size = new System.Drawing.Size(200, 20);
            this.txtWorkplace.TabIndex = 12;
            // 
            // lblPriorty
            // 
            this.lblPriorty.AutoSize = true;
            this.lblPriorty.Location = new System.Drawing.Point(44, 128);
            this.lblPriorty.Margin = new System.Windows.Forms.Padding(44, 8, 8, 8);
            this.lblPriorty.Name = "lblPriorty";
            this.lblPriorty.Size = new System.Drawing.Size(36, 13);
            this.lblPriorty.TabIndex = 4;
            this.lblPriorty.Text = "Priorty";
            // 
            // cmbPriorty
            // 
            this.cmbPriorty.BackColor = System.Drawing.SystemColors.Info;
            this.flpTop.SetFlowBreak(this.cmbPriorty, true);
            this.cmbPriorty.FormattingEnabled = true;
            this.cmbPriorty.Location = new System.Drawing.Point(92, 124);
            this.cmbPriorty.Margin = new System.Windows.Forms.Padding(4);
            this.cmbPriorty.Name = "cmbPriorty";
            this.cmbPriorty.Size = new System.Drawing.Size(200, 21);
            this.cmbPriorty.TabIndex = 21;
            // 
            // lblComment
            // 
            this.lblComment.AutoSize = true;
            this.lblComment.Location = new System.Drawing.Point(29, 157);
            this.lblComment.Margin = new System.Windows.Forms.Padding(29, 8, 8, 8);
            this.lblComment.Name = "lblComment";
            this.lblComment.Size = new System.Drawing.Size(51, 13);
            this.lblComment.TabIndex = 5;
            this.lblComment.Text = "Comment";
            // 
            // txtComment
            // 
            this.flpTop.SetFlowBreak(this.txtComment, true);
            this.txtComment.Location = new System.Drawing.Point(92, 153);
            this.txtComment.Margin = new System.Windows.Forms.Padding(4);
            this.txtComment.Name = "txtComment";
            this.txtComment.Size = new System.Drawing.Size(200, 20);
            this.txtComment.TabIndex = 13;
            // 
            // chkUseCustomIcon
            // 
            this.chkUseCustomIcon.AutoSize = true;
            this.flpTop.SetFlowBreak(this.chkUseCustomIcon, true);
            this.chkUseCustomIcon.Location = new System.Drawing.Point(93, 182);
            this.chkUseCustomIcon.Margin = new System.Windows.Forms.Padding(93, 4, 4, 4);
            this.chkUseCustomIcon.Name = "chkUseCustomIcon";
            this.chkUseCustomIcon.Size = new System.Drawing.Size(107, 17);
            this.chkUseCustomIcon.TabIndex = 17;
            this.chkUseCustomIcon.Text = "Use Custom Icon";
            this.chkUseCustomIcon.UseVisualStyleBackColor = true;
            // 
            // lblIconLocation
            // 
            this.lblIconLocation.AutoSize = true;
            this.lblIconLocation.Location = new System.Drawing.Point(8, 215);
            this.lblIconLocation.Margin = new System.Windows.Forms.Padding(8);
            this.lblIconLocation.Name = "lblIconLocation";
            this.lblIconLocation.Size = new System.Drawing.Size(72, 13);
            this.lblIconLocation.TabIndex = 6;
            this.lblIconLocation.Text = "Icon Location";
            // 
            // txtIconLocation
            // 
            this.txtIconLocation.Location = new System.Drawing.Point(92, 211);
            this.txtIconLocation.Margin = new System.Windows.Forms.Padding(4);
            this.txtIconLocation.Name = "txtIconLocation";
            this.txtIconLocation.Size = new System.Drawing.Size(200, 20);
            this.txtIconLocation.TabIndex = 14;
            // 
            // btnIconLocation
            // 
            this.flpTop.SetFlowBreak(this.btnIconLocation, true);
            this.btnIconLocation.Location = new System.Drawing.Point(304, 211);
            this.btnIconLocation.Margin = new System.Windows.Forms.Padding(8, 4, 4, 4);
            this.btnIconLocation.Name = "btnIconLocation";
            this.btnIconLocation.Size = new System.Drawing.Size(60, 20);
            this.btnIconLocation.TabIndex = 25;
            this.btnIconLocation.Text = "Browse";
            this.btnIconLocation.UseVisualStyleBackColor = false;
            // 
            // lblIconNumber
            // 
            this.lblIconNumber.AutoSize = true;
            this.lblIconNumber.Location = new System.Drawing.Point(12, 244);
            this.lblIconNumber.Margin = new System.Windows.Forms.Padding(12, 8, 8, 8);
            this.lblIconNumber.Name = "lblIconNumber";
            this.lblIconNumber.Size = new System.Drawing.Size(68, 13);
            this.lblIconNumber.TabIndex = 7;
            this.lblIconNumber.Text = "Icon Number";
            // 
            // numUpDownIconNumber
            // 
            this.numUpDownIconNumber.BackColor = System.Drawing.SystemColors.Info;
            this.numUpDownIconNumber.Location = new System.Drawing.Point(92, 240);
            this.numUpDownIconNumber.Margin = new System.Windows.Forms.Padding(4);
            this.numUpDownIconNumber.Name = "numUpDownIconNumber";
            this.numUpDownIconNumber.Size = new System.Drawing.Size(200, 20);
            this.numUpDownIconNumber.TabIndex = 19;
            this.numUpDownIconNumber.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            // 
            // btnIconNumber
            // 
            this.flpTop.SetFlowBreak(this.btnIconNumber, true);
            this.btnIconNumber.Location = new System.Drawing.Point(304, 240);
            this.btnIconNumber.Margin = new System.Windows.Forms.Padding(8, 4, 4, 4);
            this.btnIconNumber.Name = "btnIconNumber";
            this.btnIconNumber.Size = new System.Drawing.Size(60, 20);
            this.btnIconNumber.TabIndex = 26;
            this.btnIconNumber.Text = "Browse";
            this.btnIconNumber.UseVisualStyleBackColor = false;
            // 
            // lblShow
            // 
            this.lblShow.AutoSize = true;
            this.lblShow.Location = new System.Drawing.Point(46, 273);
            this.lblShow.Margin = new System.Windows.Forms.Padding(46, 8, 8, 8);
            this.lblShow.Name = "lblShow";
            this.lblShow.Size = new System.Drawing.Size(34, 13);
            this.lblShow.TabIndex = 8;
            this.lblShow.Text = "Show";
            // 
            // cmbShow
            // 
            this.cmbShow.BackColor = System.Drawing.SystemColors.Info;
            this.flpTop.SetFlowBreak(this.cmbShow, true);
            this.cmbShow.FormattingEnabled = true;
            this.cmbShow.Location = new System.Drawing.Point(92, 269);
            this.cmbShow.Margin = new System.Windows.Forms.Padding(4);
            this.cmbShow.Name = "cmbShow";
            this.cmbShow.Size = new System.Drawing.Size(200, 21);
            this.cmbShow.TabIndex = 22;
            // 
            // chkShowFolderContent
            // 
            this.chkShowFolderContent.AutoSize = true;
            this.chkShowFolderContent.Location = new System.Drawing.Point(93, 298);
            this.chkShowFolderContent.Margin = new System.Windows.Forms.Padding(93, 4, 81, 4);
            this.chkShowFolderContent.Name = "chkShowFolderContent";
            this.chkShowFolderContent.Size = new System.Drawing.Size(123, 17);
            this.chkShowFolderContent.TabIndex = 18;
            this.chkShowFolderContent.Text = "Show Files Of Folder";
            this.chkShowFolderContent.UseVisualStyleBackColor = true;
            // 
            // btnShowFolderContent
            // 
            this.flpTop.SetFlowBreak(this.btnShowFolderContent, true);
            this.btnShowFolderContent.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnShowFolderContent.Location = new System.Drawing.Point(305, 298);
            this.btnShowFolderContent.Margin = new System.Windows.Forms.Padding(8, 4, 4, 4);
            this.btnShowFolderContent.Name = "btnShowFolderContent";
            this.btnShowFolderContent.Size = new System.Drawing.Size(60, 20);
            this.btnShowFolderContent.TabIndex = 28;
            this.btnShowFolderContent.Text = "Setting";
            this.btnShowFolderContent.UseVisualStyleBackColor = false;
            // 
            // chkMouseHook
            // 
            this.chkMouseHook.AutoSize = true;
            this.chkMouseHook.Location = new System.Drawing.Point(93, 326);
            this.chkMouseHook.Margin = new System.Windows.Forms.Padding(93, 4, 4, 4);
            this.chkMouseHook.Name = "chkMouseHook";
            this.chkMouseHook.Size = new System.Drawing.Size(112, 17);
            this.chkMouseHook.TabIndex = 23;
            this.chkMouseHook.Text = "Stop Mouse Hook";
            this.chkMouseHook.UseVisualStyleBackColor = true;
            // 
            // flpLeft
            // 
            this.flpLeft.Controls.Add(this.gbIcon);
            this.flpLeft.Controls.Add(this.gbUsage);
            this.flpLeft.Dock = System.Windows.Forms.DockStyle.Left;
            this.flpLeft.Location = new System.Drawing.Point(0, 0);
            this.flpLeft.Margin = new System.Windows.Forms.Padding(4);
            this.flpLeft.Name = "flpLeft";
            this.flpLeft.Size = new System.Drawing.Size(75, 350);
            this.flpLeft.TabIndex = 3;
            // 
            // gbIcon
            // 
            this.gbIcon.Dock = System.Windows.Forms.DockStyle.Top;
            this.gbIcon.Location = new System.Drawing.Point(10, 10);
            this.gbIcon.Margin = new System.Windows.Forms.Padding(10);
            this.gbIcon.Name = "gbIcon";
            this.gbIcon.Padding = new System.Windows.Forms.Padding(0);
            this.gbIcon.Size = new System.Drawing.Size(55, 60);
            this.gbIcon.TabIndex = 1;
            this.gbIcon.TabStop = false;
            this.gbIcon.Text = "Icon";
            // 
            // gbUsage
            // 
            this.gbUsage.Dock = System.Windows.Forms.DockStyle.Top;
            this.gbUsage.Location = new System.Drawing.Point(10, 90);
            this.gbUsage.Margin = new System.Windows.Forms.Padding(10);
            this.gbUsage.Name = "gbUsage";
            this.gbUsage.Padding = new System.Windows.Forms.Padding(0);
            this.gbUsage.Size = new System.Drawing.Size(55, 60);
            this.gbUsage.TabIndex = 0;
            this.gbUsage.TabStop = false;
            this.gbUsage.Text = "Usage";
            // 
            // ButtonDialogForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.ClientSize = new System.Drawing.Size(450, 500);
            this.Name = "ButtonDialogForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.pnlContent.ResumeLayout(false);
            this.flpTop.ResumeLayout(false);
            this.flpTop.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numUpDownIconNumber)).EndInit();
            this.flpLeft.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.FlowLayoutPanel flpBottom;
        private System.Windows.Forms.FlowLayoutPanel flpLeft;
        private System.Windows.Forms.FlowLayoutPanel flpTop;
        private System.Windows.Forms.GroupBox gbIcon;
        private System.Windows.Forms.GroupBox gbUsage;
        private System.Windows.Forms.Label lblButtonType;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Label lblLocation;
        private System.Windows.Forms.Label lblWorkplace;
        private System.Windows.Forms.Label lblPriorty;
        private System.Windows.Forms.Label lblComment;
        private System.Windows.Forms.Label lblIconLocation;
        private System.Windows.Forms.Label lblIconNumber;
        private System.Windows.Forms.Label lblShow;
        private System.Windows.Forms.TextBox txtName;
        private System.Windows.Forms.TextBox txtLocation;
        private System.Windows.Forms.TextBox txtWorkplace;
        private System.Windows.Forms.TextBox txtComment;
        private System.Windows.Forms.TextBox txtIconLocation;
        private System.Windows.Forms.CheckBox chkTypeEnable;
        private System.Windows.Forms.CheckBox chkUseCustomIcon;
        private System.Windows.Forms.CheckBox chkShowFolderContent;
        private System.Windows.Forms.NumericUpDown numUpDownIconNumber;
        private System.Windows.Forms.ComboBox cmbType;
        private System.Windows.Forms.ComboBox cmbPriorty;
        private System.Windows.Forms.ComboBox cmbShow;
        private System.Windows.Forms.CheckBox chkMouseHook;
        private System.Windows.Forms.Button btnLocation;
        private System.Windows.Forms.Button btnIconLocation;
        private System.Windows.Forms.Button btnIconNumber;
        private System.Windows.Forms.Button btnShowFolderContent;
    }
}
