using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;

namespace AsynchThread
{
    class MatchDataEntity
    {
        public DataTable MatchTable { get; set; }

        public DataRow MatchRow { get; set; }

        public int TotalCount { get; set; }

        public Dictionary<string, object> KeyValues { get; set; }
    }
}
