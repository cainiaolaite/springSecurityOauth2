关于继承的三种策略
    subclass  共用一张表，分类策略
    joined-subclass  父类，子类 都有各自的表，但是子类的表必须加 外键关联
    union-subclass   父类，子类 都有各自的表，子类表中拥有父类的所有属性
        注：1.父类为抽象类的话 父类表将不会建
            2.三张表的ID 必须 为 UUID（UUID2 代替了 UUID）
