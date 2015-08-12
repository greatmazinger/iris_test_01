/**
 * 
 */
package net.veroy.analysis;
import java.util.Iterator;
import java.util.List;

import org.deri.iris.Configuration;
import org.deri.iris.EvaluationException;
import org.deri.iris.KnowledgeBaseFactory;
import org.deri.iris.api.IKnowledgeBase;
import org.deri.iris.api.basics.IQuery;
import org.deri.iris.compiler.Parser;
import org.deri.iris.compiler.ParserException;
/**
 * @author rveroy
 *
 */
public class IrisTest01 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Configuration config = KnowledgeBaseFactory.getDefaultConfiguration();
        Parser parser = new Parser();
        IKnowledgeBase kbase;
        String program = "man('homer').\n" +
                         "woman('marge').\n" +
                         "hasSon('homer','bart').\n" +
                         "isMale(?x) :- man(?x).\n" +
                         "isFemale(?x) :- woman(?x).\n" +
                         "isMale(?y) :- hasSon(?x,?y).\n" +
                         "?-isMale(?x).";
        try {
            parser.parse(program);
        } catch (ParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
             kbase = KnowledgeBaseFactory.createKnowledgeBase( parser.getFacts(),
                                                               parser.getRules(),
                                                               config );
        } catch (EvaluationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        List<IQuery> queryList = parser.getQueries();
        for (Iterator<IQuery> iter = queryList.iterator(); iter.hasNext(); ) {
            IQuery query = iter.next();
            try {
                kbase.execute(query);
            } catch (EvaluationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return;
            }
        }
    }
}
