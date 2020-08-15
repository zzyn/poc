namespace BigFrame
{
    partial class OperatorInfoForm
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
            this.bcpBlockCaptionPanel = new Common.BaseControl.BlockCaptionPanel();
            this.SuspendLayout();
            // 
            // bcpBlockCaptionPanel
            // 
            this.bcpBlockCaptionPanel.ButtonLabelBackColor = System.Drawing.Color.BlueViolet;
            this.bcpBlockCaptionPanel.ButtonLabelFocusBackColor = System.Drawing.Color.BlueViolet;
            this.bcpBlockCaptionPanel.ButtonLabelForeColor = System.Drawing.Color.White;
            this.bcpBlockCaptionPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.bcpBlockCaptionPanel.Editable = true;
            this.bcpBlockCaptionPanel.EnphasisColor = System.Drawing.Color.Yellow;
            this.bcpBlockCaptionPanel.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.bcpBlockCaptionPanel.Foldable = false;
            this.bcpBlockCaptionPanel.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.bcpBlockCaptionPanel.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.bcpBlockCaptionPanel.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.bcpBlockCaptionPanel.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(40)))), ((int)(((byte)(122)))), ((int)(((byte)(204)))));
            this.bcpBlockCaptionPanel.Location = new System.Drawing.Point(0, 0);
            this.bcpBlockCaptionPanel.Name = "bcpBlockCaptionPanel";
            this.bcpBlockCaptionPanel.Size = new System.Drawing.Size(260, 550);
            this.bcpBlockCaptionPanel.TabIndex = 0;
            this.bcpBlockCaptionPanel.Text = "Profile";
            this.bcpBlockCaptionPanel.WaitCounter = 0;
            this.bcpBlockCaptionPanel.Waiting = false;
            this.bcpBlockCaptionPanel.WarningBackColor = System.Drawing.Color.LightYellow;
            // 
            // OperatorInfoForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(260, 550);
            this.Controls.Add(this.bcpBlockCaptionPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "OperatorInfoForm";
            this.Text = "OperatorInfoForm";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private Common.BaseControl.BlockCaptionPanel bcpBlockCaptionPanel;
    }
}