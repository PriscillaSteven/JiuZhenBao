//package com.shengsiyuan.cabinetprotection.mq;
//
//import java.lang.reflect.InvocationTargetException;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//
///**
// * 创建ActiveMQConnectionFactory的工厂类
// *
// */
//public class ActiveMQConnectionFactoryFactory {
//    private static final String DEFAULT_EMBEDDED_BROKER_URL = "vm://localhost?broker.persistent=false";
//    private static final String DEFAULT_NETWORK_BROKER_URL = "http://localhost:61616";
//    private final ActiveMQProperties properties;
//
//    ActiveMQConnectionFactoryFactory(ActiveMQProperties properties) {
//        Assert.notNull(properties, "Properties must not be null");
//        this.properties = properties;
//    }
//
//    public <T extends ActiveMQConnectionFactory> T createConnectionFactory(Class<T> factoryClass) {
//        try {
//            return doCreateConnectionFactory(factoryClass);
//        } catch (Exception ex) {
//            throw new IllegalStateException("Unable to create ActiveMQConnectionFactory", ex);
//        }
//    }
//
//    private <T extends ActiveMQConnectionFactory> T doCreateConnectionFactory(Class<T> factoryClass) throws Exception {
//        ActiveMQConnectionFactory factory = createConnectionFactoryInstance(factoryClass);
//        ActiveMQProperties.Packages packages = this.properties.getPackages();
//        if (packages.getTrustAll() != null) {
//            factory.setTrustAllPackages(packages.getTrustAll().booleanValue());
//        }
//        if (!(packages.getTrusted().isEmpty())) {
//            factory.setTrustedPackages(packages.getTrusted());
//        }
//        return (T) factory;
//    }
//
//    private <T extends ActiveMQConnectionFactory> T createConnectionFactoryInstance(Class<T> factoryClass)
//            throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//        String brokerUrl = determineBrokerUrl();
//        String user = this.properties.getUser();
//        String password = this.properties.getPassword();
//        if ((StringUtils.hasLength(user)) && (StringUtils.hasLength(password))) {
//            return (T) ((ActiveMQConnectionFactory) factoryClass
//                    .getConstructor(new Class[] { String.class, String.class, String.class })
//                    .newInstance(new Object[] { user, password, brokerUrl }));
//        }
//
//        return (T) ((ActiveMQConnectionFactory) factoryClass.getConstructor(new Class[] { String.class })
//                .newInstance(new Object[] { brokerUrl }));
//    }
//
//    String determineBrokerUrl() {
//        if (this.properties.getBrokerUrl() != null) {
//            return this.properties.getBrokerUrl();
//        }
//        if (this.properties.isInMemory()) {
//            return "vm://localhost?broker.persistent=false";
//        }
//        return "tcp://localhost:61616";
//    }
//}