import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const Header = ({ title }) => {
  return (
    <View style={styles.header}>
      <Text style={styles.title}>{title}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  header: {
    backgroundColor: '#3498db', // Altere a cor de fundo conforme necessário
    paddingVertical: 15,
    alignItems: 'center',
  },
  title: {
    fontSize: 24,
    color: 'white', // Altere a cor do texto conforme necessário
    fontWeight: 'bold',
  },
});

export default Header;
