namespace Common.BaseUI
{
    partial class BaseDialogForm
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
            this.pnlBottom = new System.Windows.Forms.Panel();
            this.flpBottomButtton = new System.Windows.Forms.FlowLayoutPanel();
            this.btnAccept = new System.Windows.Forms.Button();
            this.btnCancle = new System.Windows.Forms.Button();
            this.pnlContent = new System.Windows.Forms.Panel();
            this.pnlBottom.SuspendLayout();
            this.flpBottomButtton.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlBottom
            // 
            this.pnlBottom.BackColor = System.Drawing.SystemColors.MenuHighlight;
            this.pnlBottom.Controls.Add(this.flpBottomButtton);
            this.pnlBottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlBottom.Location = new System.Drawing.Point(0, 275);
            this.pnlBottom.Margin = new System.Windows.Forms.Padding(4);
            this.pnlBottom.Name = "pnlBottom";
            this.pnlBottom.Size = new System.Drawing.Size(300, 25);
            this.pnlBottom.TabIndex = 0;
            // 
            // flpBottomButtton
            // 
            this.flpBottomButtton.Controls.Add(this.btnAccept);
            this.flpBottomButtton.Controls.Add(this.btnCancle);
            this.flpBottomButtton.Dock = System.Windows.Forms.DockStyle.Right;
            this.flpBottomButtton.Location = new System.Drawing.Point(130, 0);
            this.flpBottomButtton.Margin = new System.Windows.Forms.Padding(0);
            this.flpBottomButtton.Name = "flpBottomButtton";
            this.flpBottomButtton.Size = new System.Drawing.Size(170, 25);
            this.flpBottomButtton.TabIndex = 2;
            // 
            // btnAccept
            // 
            this.btnAccept.BackColor = System.Drawing.SystemColors.Control;
            this.btnAccept.DialogResult = System.Windows.Forms.DialogResult.OK;
            this.btnAccept.FlatAppearance.BorderSize = 0;
            this.btnAccept.Location = new System.Drawing.Point(4, 0);
            this.btnAccept.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.btnAccept.Name = "btnAccept";
            this.btnAccept.Size = new System.Drawing.Size(75, 25);
            this.btnAccept.TabIndex = 0;
            this.btnAccept.Text = "OK";
            this.btnAccept.UseVisualStyleBackColor = false;
            this.btnAccept.Click += new System.EventHandler(this.OnAcceptClick);
            // 
            // btnCancle
            // 
            this.btnCancle.BackColor = System.Drawing.SystemColors.Control;
            this.btnCancle.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancle.FlatAppearance.BorderSize = 0;
            this.btnCancle.Location = new System.Drawing.Point(87, 0);
            this.btnCancle.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.btnCancle.Name = "btnCancle";
            this.btnCancle.Size = new System.Drawing.Size(75, 25);
            this.btnCancle.TabIndex = 1;
            this.btnCancle.Text = "Cancle";
            this.btnCancle.UseVisualStyleBackColor = false;
            this.btnCancle.Click += new System.EventHandler(this.OnCancleClick);
            // 
            // pnlContent
            // 
            this.pnlContent.BackColor = System.Drawing.SystemColors.Window;
            this.pnlContent.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlContent.Location = new System.Drawing.Point(0, 25);
            this.pnlContent.Name = "pnlContent";
            this.pnlContent.Size = new System.Drawing.Size(300, 250);
            this.pnlContent.TabIndex = 0;
            // 
            // BaseDialogForm
            // 
            this.AcceptButton = this.btnAccept;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.CancelButton = this.btnCancle;
            this.ClientSize = new System.Drawing.Size(300, 300);
            this.Controls.Add(this.pnlContent);
            this.Controls.Add(this.pnlBottom);
            this.Name = "BaseDialogForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Controls.SetChildIndex(this.pnlCaption, 0);
            this.Controls.SetChildIndex(this.pnlBottom, 0);
            this.Controls.SetChildIndex(this.pnlContent, 0);
            this.pnlBottom.ResumeLayout(false);
            this.flpBottomButtton.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        
        protected System.Windows.Forms.FlowLayoutPanel flpBottomButtton;
        protected System.Windows.Forms.Button btnCancle;
        protected System.Windows.Forms.Button btnAccept;
        protected System.Windows.Forms.Panel pnlBottom;
        protected System.Windows.Forms.Panel pnlContent;
    }
}