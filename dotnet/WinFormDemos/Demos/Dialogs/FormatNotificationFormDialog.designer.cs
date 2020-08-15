namespace Demo
{
    partial class FormatNotificationFormDialog
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
            this.components = new System.ComponentModel.Container();
            this.ctrErrorProvider = new System.Windows.Forms.ErrorProvider(this.components);
            this.lblApplication = new System.Windows.Forms.Label();
            this.txtApplication = new System.Windows.Forms.TextBox();
            this.fpnlMain = new System.Windows.Forms.FlowLayoutPanel();
            ((System.ComponentModel.ISupportInitialize)(this.ctrErrorProvider)).BeginInit();
            this.fpnlMain.SuspendLayout();
            this.SuspendLayout();
            // 
            // ctrErrorProvider
            // 
            this.ctrErrorProvider.ContainerControl = this;
            // 
            // lblApplication
            // 
            this.lblApplication.AutoSize = true;
            this.lblApplication.Location = new System.Drawing.Point(8, 8);
            this.lblApplication.Margin = new System.Windows.Forms.Padding(8, 8, 0, 8);
            this.lblApplication.Name = "lblApplication";
            this.lblApplication.Size = new System.Drawing.Size(90, 13);
            this.lblApplication.TabIndex = 2;
            this.lblApplication.Text = "Application Name";
            this.lblApplication.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // txtApplication
            // 
            this.txtApplication.Location = new System.Drawing.Point(102, 4);
            this.txtApplication.Margin = new System.Windows.Forms.Padding(4);
            this.txtApplication.Name = "txtApplication";
            this.txtApplication.Size = new System.Drawing.Size(180, 20);
            this.txtApplication.TabIndex = 3;
            this.txtApplication.Validating += new System.ComponentModel.CancelEventHandler(this.OnValidating);
            // 
            // fpnlMain
            // 
            this.fpnlMain.Controls.Add(this.lblApplication);
            this.fpnlMain.Controls.Add(this.txtApplication);
            this.fpnlMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.fpnlMain.Location = new System.Drawing.Point(0, 0);
            this.fpnlMain.Margin = new System.Windows.Forms.Padding(0);
            this.fpnlMain.Name = "fpnlMain";
            this.fpnlMain.Size = new System.Drawing.Size(384, 136);
            this.fpnlMain.TabIndex = 4;
            // 
            // FormatNotificationFormDialog
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(384, 166);
            this.Controls.Add(this.fpnlMain);
            this.Name = "FormatNotificationFormDialog";
            this.Text = "FormatNotificationForm";
            this.Controls.SetChildIndex(this.fpnlMain, 0);
            ((System.ComponentModel.ISupportInitialize)(this.ctrErrorProvider)).EndInit();
            this.fpnlMain.ResumeLayout(false);
            this.fpnlMain.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ErrorProvider ctrErrorProvider;
        private System.Windows.Forms.Label lblApplication;
        private System.Windows.Forms.TextBox txtApplication;
        private System.Windows.Forms.FlowLayoutPanel fpnlMain;
    }
}