package org.metaborg.runtime.task.primitives;

import java.util.Set;

import org.metaborg.runtime.task.engine.ITaskEngine;
import org.spoofax.interpreter.core.IContext;
import org.spoofax.interpreter.core.InterpreterException;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.stratego.Strategy;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class task_api_sources_of_0_1 extends TaskEnginePrimitive {
    public static task_api_sources_of_0_1 instance = new task_api_sources_of_0_1();

    public task_api_sources_of_0_1() {
        super("task_api_sources_of", 0, 1);
    }

    @Override public boolean call(ITaskEngine taskEngine, IContext env, Strategy[] svars, IStrategoTerm[] tvars)
        throws InterpreterException {
        final IStrategoTerm taskIDOrTaskIDS = tvars[0];

        final Set<IStrategoTerm> sources = Sets.newHashSet();
        if(Tools.isTermList(taskIDOrTaskIDS)) {
            for(IStrategoTerm taskID : taskIDOrTaskIDS) {
                Iterables.addAll(sources, taskEngine.getSourcesOf(taskID));
            }
        } else {
            Iterables.addAll(sources, taskEngine.getSourcesOf(taskIDOrTaskIDS));
        }

        env.setCurrent(env.getFactory().makeList(sources));

        return true;
    }
}
