namespace Common.BaseUI
{
    partial class BaseForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(BaseForm));
            this.pnlCaption = new System.Windows.Forms.Panel();
            this.flpCaptionTitle = new System.Windows.Forms.FlowLayoutPanel();
            this.lblTitle = new System.Windows.Forms.Label();
            this.flpCaptionButton = new System.Windows.Forms.FlowLayoutPanel();
            this.btnMin = new System.Windows.Forms.Button();
            this.btnMax = new System.Windows.Forms.Button();
            this.btnClose = new System.Windows.Forms.Button();
            this.flpCaptionIcon = new System.Windows.Forms.FlowLayoutPanel();
            this.picIcon = new System.Windows.Forms.PictureBox();
            this.pnlCaption.SuspendLayout();
            this.flpCaptionTitle.SuspendLayout();
            this.flpCaptionButton.SuspendLayout();
            this.flpCaptionIcon.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.picIcon)).BeginInit();
            this.SuspendLayout();
            // 
            // pnlCaption
            // 
            this.pnlCaption.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.pnlCaption.Controls.Add(this.flpCaptionTitle);
            this.pnlCaption.Controls.Add(this.flpCaptionButton);
            this.pnlCaption.Controls.Add(this.flpCaptionIcon);
            this.pnlCaption.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlCaption.Location = new System.Drawing.Point(0, 0);
            this.pnlCaption.Name = "pnlCaption";
            this.pnlCaption.Size = new System.Drawing.Size(400, 25);
            this.pnlCaption.TabIndex = 2;
            // 
            // flpCaptionTitle
            // 
            this.flpCaptionTitle.Controls.Add(this.lblTitle);
            this.flpCaptionTitle.Dock = System.Windows.Forms.DockStyle.Fill;
            this.flpCaptionTitle.Location = new System.Drawing.Point(20, 0);
            this.flpCaptionTitle.Margin = new System.Windows.Forms.Padding(4);
            this.flpCaptionTitle.Name = "flpCaptionTitle";
            this.flpCaptionTitle.Size = new System.Drawing.Size(320, 25);
            this.flpCaptionTitle.TabIndex = 1;
            this.flpCaptionTitle.DoubleClick += new System.EventHandler(this.OnMaximized);
            this.flpCaptionTitle.MouseDown += new System.Windows.Forms.MouseEventHandler(this.OnMouseDown);
            this.flpCaptionTitle.MouseMove += new System.Windows.Forms.MouseEventHandler(this.OnMouseMove);
            this.flpCaptionTitle.MouseUp += new System.Windows.Forms.MouseEventHandler(this.OnMouseUp);
            // 
            // lblTitle
            // 
            this.lblTitle.AutoSize = true;
            this.lblTitle.Location = new System.Drawing.Point(4, 6);
            this.lblTitle.Margin = new System.Windows.Forms.Padding(4, 6, 4, 6);
            this.lblTitle.Name = "lblTitle";
            this.lblTitle.Size = new System.Drawing.Size(0, 13);
            this.lblTitle.TabIndex = 0;
            this.lblTitle.MouseDown += new System.Windows.Forms.MouseEventHandler(this.OnMouseDown);
            this.lblTitle.MouseMove += new System.Windows.Forms.MouseEventHandler(this.OnMouseMove);
            this.lblTitle.MouseUp += new System.Windows.Forms.MouseEventHandler(this.OnMouseUp);
            // 
            // flpCaptionButton
            // 
            this.flpCaptionButton.Controls.Add(this.btnMin);
            this.flpCaptionButton.Controls.Add(this.btnMax);
            this.flpCaptionButton.Controls.Add(this.btnClose);
            this.flpCaptionButton.Dock = System.Windows.Forms.DockStyle.Right;
            this.flpCaptionButton.Location = new System.Drawing.Point(340, 0);
            this.flpCaptionButton.Margin = new System.Windows.Forms.Padding(4);
            this.flpCaptionButton.Name = "flpCaptionButton";
            this.flpCaptionButton.Size = new System.Drawing.Size(60, 25);
            this.flpCaptionButton.TabIndex = 2;
            // 
            // btnMin
            // 
            this.btnMin.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btnMin.BackgroundImage")));
            this.btnMin.Dock = System.Windows.Forms.DockStyle.Top;
            this.btnMin.FlatAppearance.BorderSize = 0;
            this.btnMin.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnMin.Location = new System.Drawing.Point(2, 4);
            this.btnMin.Margin = new System.Windows.Forms.Padding(2, 4, 2, 4);
            this.btnMin.Name = "btnMin";
            this.btnMin.Size = new System.Drawing.Size(16, 16);
            this.btnMin.TabIndex = 1;
            this.btnMin.UseVisualStyleBackColor = true;
            this.btnMin.Click += new System.EventHandler(this.OnMinimized);
            // 
            // btnMax
            // 
            this.btnMax.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btnMax.BackgroundImage")));
            this.btnMax.Dock = System.Windows.Forms.DockStyle.Top;
            this.btnMax.FlatAppearance.BorderSize = 0;
            this.btnMax.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnMax.Location = new System.Drawing.Point(22, 4);
            this.btnMax.Margin = new System.Windows.Forms.Padding(2, 4, 2, 4);
            this.btnMax.Name = "btnMax";
            this.btnMax.Size = new System.Drawing.Size(16, 16);
            this.btnMax.TabIndex = 2;
            this.btnMax.UseVisualStyleBackColor = true;
            this.btnMax.Click += new System.EventHandler(this.OnMaximized);
            // 
            // btnClose
            // 
            this.btnClose.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btnClose.BackgroundImage")));
            this.btnClose.Dock = System.Windows.Forms.DockStyle.Top;
            this.btnClose.FlatAppearance.BorderSize = 0;
            this.btnClose.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnClose.Location = new System.Drawing.Point(42, 4);
            this.btnClose.Margin = new System.Windows.Forms.Padding(2, 4, 2, 4);
            this.btnClose.Name = "btnClose";
            this.btnClose.Size = new System.Drawing.Size(16, 16);
            this.btnClose.TabIndex = 0;
            this.btnClose.UseVisualStyleBackColor = true;
            this.btnClose.Click += new System.EventHandler(this.OnClose);
            // 
            // flpCaptionIcon
            // 
            this.flpCaptionIcon.Controls.Add(this.picIcon);
            this.flpCaptionIcon.Dock = System.Windows.Forms.DockStyle.Left;
            this.flpCaptionIcon.Location = new System.Drawing.Point(0, 0);
            this.flpCaptionIcon.Margin = new System.Windows.Forms.Padding(4);
            this.flpCaptionIcon.Name = "flpCaptionIcon";
            this.flpCaptionIcon.Size = new System.Drawing.Size(20, 25);
            this.flpCaptionIcon.TabIndex = 0;
            this.flpCaptionIcon.DoubleClick += new System.EventHandler(this.OnMaximized);
            this.flpCaptionIcon.MouseDown += new System.Windows.Forms.MouseEventHandler(this.OnMouseDown);
            this.flpCaptionIcon.MouseMove += new System.Windows.Forms.MouseEventHandler(this.OnMouseMove);
            this.flpCaptionIcon.MouseUp += new System.Windows.Forms.MouseEventHandler(this.OnMouseUp);
            // 
            // picIcon
            // 
            this.picIcon.Image = ((System.Drawing.Image)(resources.GetObject("picIcon.Image")));
            this.picIcon.Location = new System.Drawing.Point(2, 4);
            this.picIcon.Margin = new System.Windows.Forms.Padding(2, 4, 2, 4);
            this.picIcon.Name = "picIcon";
            this.picIcon.Size = new System.Drawing.Size(16, 16);
            this.picIcon.TabIndex = 3;
            this.picIcon.TabStop = false;
            this.picIcon.DoubleClick += new System.EventHandler(this.OnMaximized);
            this.picIcon.MouseDown += new System.Windows.Forms.MouseEventHandler(this.OnMouseDown);
            this.picIcon.MouseMove += new System.Windows.Forms.MouseEventHandler(this.OnMouseMove);
            this.picIcon.MouseUp += new System.Windows.Forms.MouseEventHandler(this.OnMouseUp);
            // 
            // BaseForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(400, 400);
            this.Controls.Add(this.pnlCaption);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "BaseForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            
            this.pnlCaption.ResumeLayout(false);
            this.flpCaptionTitle.ResumeLayout(false);
            this.flpCaptionTitle.PerformLayout();
            this.flpCaptionButton.ResumeLayout(false);
            this.flpCaptionIcon.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.picIcon)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        protected System.Windows.Forms.FlowLayoutPanel flpCaptionTitle;
        protected System.Windows.Forms.Label lblTitle;
        protected System.Windows.Forms.FlowLayoutPanel flpCaptionIcon;
        protected System.Windows.Forms.PictureBox picIcon;
        protected System.Windows.Forms.FlowLayoutPanel flpCaptionButton;
        protected System.Windows.Forms.Panel pnlCaption;
        protected System.Windows.Forms.Button btnMax;
        protected System.Windows.Forms.Button btnMin;
        protected System.Windows.Forms.Button btnClose;
    }
}