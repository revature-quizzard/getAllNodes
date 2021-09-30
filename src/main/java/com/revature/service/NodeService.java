package com.revature.service;

import com.revature.models.Node;

public class NodeService {
    /**
     * A validator to ensure the argument is a subforum, and has no ancestors.
     *
     * @param parent The object undergoing the validation check.
     *
     *
     * @return Returns true if it's a subforum, false if it's not.
     *
     * @author Hiroshi Nobuoka
     */

    public static boolean isValidParent(Node parent){
        return parent.getAncestors() == null || parent.getAncestors().size() == 0;
    }
}
