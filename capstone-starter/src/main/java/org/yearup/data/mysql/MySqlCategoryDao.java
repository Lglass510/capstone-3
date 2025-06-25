package org.yearup.data.mysql;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        // get all categories
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet row = preparedStatement.executeQuery();
            while (row.next()) {
                Category category = mapRow(row);
                categories.add(category);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public Category getById(int categoryId) {
        // get category by id
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        List<Category> categories = new ArrayList<>();
        try {
            Connection connection = this.dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            ResultSet row = preparedStatement.executeQuery();
            while (row.next()) {
                Category category = mapRow(row);
                categories.add(category);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(categories.size() > 1){
            throw new RuntimeException("Unexpected number of categories");
        }
        if(categories.size() == 0){
            return null;
        } else {
            return categories.get(0);
        }
    }

    @Override
    public Category create(Category category) {
        // create a new category
      String sql = "INSERT INTO categories (name, description) VALUES (?,?)";
      try(Connection connection = getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
          preparedStatement.setString(1, category.getName());
          preparedStatement.setString(2, category.getDescription());

          int affectedRows = preparedStatement.executeUpdate();
          ResultSet resultSet = preparedStatement.getGeneratedKeys();
          resultSet.next();
          int primaryKey = resultSet.getInt(1);
          category.setCategoryId(primaryKey);
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
      return category;
    }

    @Override
    public void update(int categoryId, Category category) {
        // update category
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getDescription());
            preparedStatement.setInt(2,categoryId);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(int categoryId) {
        // delete category
        String sql = "DELETE FROM categories WHERE category_id = ?";
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet row) throws SQLException {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description);

        return category;
    }

}
