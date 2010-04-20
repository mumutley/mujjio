/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mutuality.herql;

import com.google.inject.Inject;
import net.guts.event.Channel;

/**
 *
 * @author manzoors
 */
public class PrimitiveSupplier {

    private final Channel<Integer> channel;

    @Inject
    public PrimitiveSupplier(Channel<Integer> channel) {
        this.channel = channel;
    }

    public void generate(int... numbers) {
        for (int i : numbers) {
            channel.publish(i);
            System.out.println(i);
        }
    }
}
