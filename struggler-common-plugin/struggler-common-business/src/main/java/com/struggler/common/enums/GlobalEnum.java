package com.struggler.common.enums;

/**
 * @author wanglp
 * @date 2019/6/8
 **/
public class GlobalEnum {

    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG("10"),
        /**
         * 菜单
         */
        MENU("20"),
        /**
         * 按钮
         */
        BUTTON("30");

        private String value;

        MenuType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
