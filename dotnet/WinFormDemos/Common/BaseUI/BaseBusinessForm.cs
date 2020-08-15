using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Common;
using Common.BaseControl;
using Common.Entity;

namespace Common.BaseUI
{
    public partial class BaseBusinessForm : BaseDesignForm
    {
        public BaseBusinessForm()
        {
            InitializeComponent();
        }

        public BusinessEntity BusinessInfo { get; set; }

        public DataSet MainDataSet { get; set; }

        public delegate void DataBackHandler(Dictionary<string, object> param);

        protected delegate void FormLoadCompletedHandler(bool IsSuccessed);


        
    }

   
}
