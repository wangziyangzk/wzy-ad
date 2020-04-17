package cn.wzy.exception;

import cn.wzy.vo.CodeMsg;

public class AdException extends Exception {
    private static final long serialVersionUID = 1L;
    private CodeMsg cm;

    public AdException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
