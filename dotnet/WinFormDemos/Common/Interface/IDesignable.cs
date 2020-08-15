using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Drawing;

namespace Common
{
    
    public interface IDesignable
    {
        
        Font LabelFont { get; }

        Color LabelForeColor { get; }

        Color GotFocusLabelForeColor { get; }

        Color ButtonLabelBackColor { get; }

        Color ButtonLabelForeColor { get; }

        Color ButtonLabelFocusBackColor { get; }

        Color WarningBackColor { get; }

        Color ErrorBackColor { get; }

        Color GotFocusBackColor { get; }

        void OnLabelFontChanged(EventArgs e);

        void OnParentLabelFontChanged(EventArgs e);


    }
}
