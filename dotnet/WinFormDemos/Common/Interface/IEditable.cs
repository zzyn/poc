using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Common
{
    public interface IEditable
    {
        bool Editable { get; set; }

        void OnEditableChanged(EventArgs e);
        
        void OnParentEditableChanged(EventArgs e);
    }
}
