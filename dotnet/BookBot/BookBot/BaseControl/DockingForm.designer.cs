
namespace Common.BaseControl
{
    partial class DockingForm
    {

        private System.ComponentModel.IContainer components = null;


        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region



        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DockingForm));
            this.popMenu = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.popMenuClose = new System.Windows.Forms.ToolStripMenuItem();
            this.popMenuCloseOther = new System.Windows.Forms.ToolStripMenuItem();
            this.popMenuExpandeAll = new System.Windows.Forms.ToolStripMenuItem();
            this.popMenuOpenAllPage = new System.Windows.Forms.ToolStripMenuItem();
            this.popMenuCloseAllPage = new System.Windows.Forms.ToolStripMenuItem();
            this.dockingTab = new Common.BaseControl.DockingTab();
            this.popMenu.SuspendLayout();
            this.SuspendLayout();
            // 
            // popMenu
            // 
            this.popMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.popMenuClose,
            this.popMenuCloseOther,
            this.popMenuExpandeAll,
            this.popMenuOpenAllPage,
            this.popMenuCloseAllPage});
            this.popMenu.Name = "popMenu";
            this.popMenu.Size = new System.Drawing.Size(150, 114);
            this.popMenu.PreviewKeyDown += new System.Windows.Forms.PreviewKeyDownEventHandler(this.popMenu_PreviewKeyDown);
            // 
            // popMenuClose
            // 
            this.popMenuClose.Name = "popMenuClose";
            this.popMenuClose.Size = new System.Drawing.Size(215, 22);
            this.popMenuClose.Text = "Close";
            this.popMenuClose.Click += new System.EventHandler(this.popMenuClose_Click);
            // 
            // popMenuCloseOther
            // 
            this.popMenuCloseOther.Name = "popMenuCloseOther";
            this.popMenuCloseOther.Size = new System.Drawing.Size(169, 22);
            this.popMenuCloseOther.Text = "Close Other";
            this.popMenuCloseOther.Click += new System.EventHandler(this.popMenuCloseOther_Click);
            // 
            // popMenuExpandeAll
            // 
            this.popMenuExpandeAll.Name = "popMenuExpandeAll";
            this.popMenuExpandeAll.Size = new System.Drawing.Size(169, 22);
            this.popMenuExpandeAll.Text = "Expande All";
            this.popMenuExpandeAll.Click += new System.EventHandler(this.popMenuExpandeAll_Click);
            // 
            // popMenuOpenAllPage
            // 
            this.popMenuOpenAllPage.Name = "popMenuOpenAllPage";
            this.popMenuOpenAllPage.Size = new System.Drawing.Size(169, 22);
            this.popMenuOpenAllPage.Text = "Open All Page";
            this.popMenuOpenAllPage.Visible = false;
            this.popMenuOpenAllPage.Click += new System.EventHandler(this.popMenuOpenAllPage_Click);
            // 
            // popMenuCloseAllPage
            // 
            this.popMenuCloseAllPage.Name = "popMenuCloseAllPage";
            this.popMenuCloseAllPage.Size = new System.Drawing.Size(149, 22);
            this.popMenuCloseAllPage.Text = "Close All Page";
            this.popMenuCloseAllPage.Visible = false;
            this.popMenuCloseAllPage.Click += new System.EventHandler(this.popMenuCloseAllPage_Click);
            // 
            // dockingTab
            // 
            this.dockingTab.AllowDrop = true;
            this.dockingTab.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.dockingTab.ButtonLabelBackColor = System.Drawing.Color.Green;
            this.dockingTab.ButtonLabelFocusBackColor = System.Drawing.Color.Orange;
            this.dockingTab.ButtonLabelForeColor = System.Drawing.Color.White;
            this.dockingTab.Dock = System.Windows.Forms.DockStyle.Fill;
            this.dockingTab.Editable = true;
            this.dockingTab.EnphasisColor = System.Drawing.Color.Yellow;
            this.dockingTab.ErrorBackColor = System.Drawing.Color.SeaShell;
            this.dockingTab.Foldable = false;
            this.dockingTab.GotFocusBackColor = System.Drawing.Color.LemonChiffon;
            this.dockingTab.GotFocusLabelForeColor = System.Drawing.Color.Brown;
            this.dockingTab.HoverControl = null;
            this.dockingTab.LabelFont = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.dockingTab.LabelForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(0)))), ((int)(((byte)(102)))), ((int)(((byte)(0)))));
            this.dockingTab.Location = new System.Drawing.Point(0, 0);
            this.dockingTab.Name = "dockingTab";
            this.dockingTab.SelectedTabControl = null;
            this.dockingTab.SelectedTabIndex = -1;
            this.dockingTab.Size = new System.Drawing.Size(421, 425);
            this.dockingTab.TabIndex = 1;
            this.dockingTab.WaitCounter = 0;
            this.dockingTab.Waiting = false;
            this.dockingTab.WarningBackColor = System.Drawing.Color.LightYellow;
            this.dockingTab.ControlAdded += new System.Windows.Forms.ControlEventHandler(this.dockingTab_ControlAdded);
            this.dockingTab.ControlRemoved += new System.Windows.Forms.ControlEventHandler(this.dockingTab_ControlRemoved);
            this.dockingTab.MouseDown += new System.Windows.Forms.MouseEventHandler(this.dockingTab_MouseDown);
            this.dockingTab.MouseUp += new System.Windows.Forms.MouseEventHandler(this.dockingTab_MouseUp);
            // 
            // DockingForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(421, 425);
            this.Controls.Add(this.dockingTab);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "DockingForm";
            this.popMenu.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ContextMenuStrip popMenu;
        private System.Windows.Forms.ToolStripMenuItem popMenuClose;
        private System.Windows.Forms.ToolStripMenuItem popMenuCloseOther;
        private DockingTab dockingTab;
        private System.Windows.Forms.ToolStripMenuItem popMenuExpandeAll;
        private System.Windows.Forms.ToolStripMenuItem popMenuOpenAllPage;
        private System.Windows.Forms.ToolStripMenuItem popMenuCloseAllPage;
    }
}