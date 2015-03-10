/*******************************************************************************
 * Copyright (c) 2013 Jeremy.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * If you'd like to obtain a another license to this code, you may contact Jeremy to discuss alternative redistribution options.
 * 
 * Contributors:
 *     Jeremy - initial API and implementation
 ******************************************************************************/
package io.github.jevaengine.builder.animationbuilder;

import io.github.jevaengine.builder.animationbuilder.ui.FloatingToolbarFactory;
import io.github.jevaengine.game.DefaultGame;
import io.github.jevaengine.graphics.IRenderable;
import io.github.jevaengine.graphics.ISpriteFactory;
import io.github.jevaengine.graphics.ISpriteFactory.SpriteConstructionException;
import io.github.jevaengine.graphics.NullGraphic;
import io.github.jevaengine.joystick.IInputSource;
import io.github.jevaengine.math.Vector2D;
import io.github.jevaengine.ui.IWindowFactory;
import io.github.jevaengine.ui.IWindowFactory.WindowConstructionException;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnimationBuilder extends DefaultGame
{
	private IRenderable m_cursor;
	
	private Logger m_logger = LoggerFactory.getLogger(AnimationBuilder.class);
	
	public AnimationBuilder(IInputSource inputSource, ISpriteFactory spriteFactory, IWindowFactory windowFactory, Vector2D resolution, URI baseDirectory)
	{
		super(inputSource, resolution);
		
		try
		{
			m_cursor = spriteFactory.create(URI.create("local:///ui/style/tech/cursor/cursor.jsf"));
		} catch (SpriteConstructionException e)
		{
			m_logger.error("Error constructing cursor sprite. Reverting to null graphic for cursor.", e);
			m_cursor = new NullGraphic();
		}
		
		try
		{
			new FloatingToolbarFactory(getWindowManager(), windowFactory, spriteFactory, baseDirectory).create().center();
		} catch (WindowConstructionException e)
		{
			m_logger.error("Error constructing world builder toolbar.", e);
		}
	}

	@Override
	protected IRenderable getCursor()
	{
		return m_cursor;
	}

	@Override
	protected void doLogic(int deltaTime) { }
}