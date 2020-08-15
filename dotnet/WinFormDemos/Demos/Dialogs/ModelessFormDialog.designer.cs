namespace Demo
{
    partial class ModelessFormDialog
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
            this.btnAccept = new System.Windows.Forms.Button();
            this.btnCancle = new System.Windows.Forms.Button();
            this.fpnlBottom = new System.Windows.Forms.FlowLayoutPanel();
            this.fpnlBottom.SuspendLayout();
            this.SuspendLayout();
            // 
            // btnAccept
            // 
            this.btnAccept.Location = new System.Drawing.Point(225, 3);
            this.btnAccept.Name = "btnAccept";
            this.btnAccept.Size = new System.Drawing.Size(75, 23);
            this.btnAccept.TabIndex = 0;
            this.btnAccept.Text = "OK";
            this.btnAccept.UseVisualStyleBackColor = true;
            this.btnAccept.Click += new System.EventHandler(this.btnAccept_Click);
            // 
            // btnCancle
            // 
            this.btnCancle.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancle.Location = new System.Drawing.Point(306, 3);
            this.btnCancle.Name = "btnCancle";
            this.btnCancle.Size = new System.Drawing.Size(75, 23);
            this.btnCancle.TabIndex = 1;
            this.btnCancle.Text = "Cancle";
            this.btnCancle.UseVisualStyleBackColor = true;
            this.btnCancle.Click += new System.EventHandler(this.btnClose_Click);
            // 
            // fpnlBottom
            // 
            this.fpnlBottom.Controls.Add(this.btnCancle);
            this.fpnlBottom.Controls.Add(this.btnAccept);
            this.fpnlBottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.fpnlBottom.FlowDirection = System.Windows.Forms.FlowDirection.RightToLeft;
            this.fpnlBottom.Location = new System.Drawing.Point(0, 136);
            this.fpnlBottom.Margin = new System.Windows.Forms.Padding(0);
            this.fpnlBottom.Name = "fpnlBottom";
            this.fpnlBottom.Size = new System.Drawing.Size(384, 30);
            this.fpnlBottom.TabIndex = 2;
            // 
            // ModelessFormDialog
            // 
            this.AcceptButton = this.btnAccept;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.btnCancle;
            this.ClientSize = new System.Drawing.Size(384, 166);
            this.Controls.Add(this.fpnlBottom);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.SizableToolWindow;
            this.Name = "ModelessFormDialog";
            this.Text = "ModelessForm";
            this.fpnlBottom.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btnAccept;
        private System.Windows.Forms.Button btnCancle;
        private System.Windows.Forms.FlowLayoutPanel fpnlBottom;
    }
}