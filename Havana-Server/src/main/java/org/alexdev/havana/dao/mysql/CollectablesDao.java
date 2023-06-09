package org.alexdev.havana.dao.mysql;

import org.alexdev.havana.dao.Storage;
import org.alexdev.havana.game.catalogue.collectables.CollectableData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CollectablesDao {
    public static List<CollectableData> getCollectablesData() {
        List<CollectableData> collectableData = new ArrayList<>();

        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            sqlConnection = Storage.getStorage().getConnection();
            preparedStatement = Storage.getStorage().prepare("SELECT * FROM catalogue_collectables", sqlConnection);
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()) {
                collectableData.add(new CollectableData(resultSet.getInt("store_page"), resultSet.getInt("admin_page"), resultSet.getLong("expiry"),
                        resultSet.getLong("lifetime"), resultSet.getInt("current_position"), resultSet.getString("class_names").split(",")));
            }

        } catch (Exception e) {
            Storage.logError(e);
        } finally {
            Storage.closeSilently(preparedStatement);
            Storage.closeSilently(sqlConnection);
            Storage.closeSilently(resultSet);
        }

        return collectableData;
    }

    public static void saveData(int storePage, int currentPosition, long expiry) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = Storage.getStorage().getConnection();
            preparedStatement = Storage.getStorage().prepare("UPDATE catalogue_collectables SET expiry = ?, current_position = ? WHERE store_page = ?", sqlConnection);
            preparedStatement.setLong(1, expiry);
            preparedStatement.setInt(2, currentPosition);
            preparedStatement.setInt(3, storePage);
            preparedStatement.execute();

        } catch (Exception e) {
            Storage.logError(e);
        } finally {
            Storage.closeSilently(preparedStatement);
            Storage.closeSilently(sqlConnection);
        }
    }

    public static void updateClassNames(String classNames, int storePage) {
        Connection sqlConnection = null;
        PreparedStatement preparedStatement = null;

        try {
            sqlConnection = Storage.getStorage().getConnection();
            preparedStatement = Storage.getStorage().prepare("UPDATE catalogue_collectables SET class_names = ? WHERE store_page = ?", sqlConnection);
            preparedStatement.setString(1, classNames);
            preparedStatement.setInt(2, storePage);
            preparedStatement.execute();

        } catch (Exception e) {
            Storage.logError(e);
        } finally {
            Storage.closeSilently(preparedStatement);
            Storage.closeSilently(sqlConnection);
        }
    }
}
